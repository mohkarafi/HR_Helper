package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.PaySlip;
import org.example.emplyeemanagment.Enums.paySlipStatus;
import org.example.emplyeemanagment.Mappers.PaySlipMapper;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.PaySlipRepository;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.NotificationService;
import org.example.emplyeemanagment.Service.PaySlipService;
import org.example.emplyeemanagment.dtos.EmailDetails;
import org.example.emplyeemanagment.dtos.PaySlipDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PaySlipServiceImpl implements PaySlipService {
    private final PaySlipRepository paySlipRepository;
    private final EmployeeRepository employeeRepository;
    private final NotificationService notificationService;

    @Override
    public StandardResponse AddPayroll(PaySlipDto paySlipDto) {
        try {
            PaySlip paySlip = PaySlipMapper.mapToPayroll(paySlipDto);
            Employee employee = employeeRepository.findById(paySlipDto.getEmployeeId()).orElseThrow(() -> new Exception("Employee not found"));
            paySlip.setEmployee(employee);
            PaySlip savePaySlip = paySlipRepository.save(paySlip);
            return StandardResponse.builder()
                    .code("200")
                    .status("success")
                    .data(PaySlipMapper.mapToPayrollDto(savePaySlip))
                    .build();
        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("error : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public StandardResponse UpdatePayroll(Long id, PaySlipDto paySlipDto) {
        try {
            PaySlip paySlip = paySlipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payroll not found"));
            Employee employee = employeeRepository.findById(paySlip.getEmployee().getId()).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
            paySlip.setId(paySlipDto.getId());
            paySlip.setPayrollDate(paySlipDto.getPayrollDate());
            paySlip.setBaseSalary(paySlipDto.getBaseSalary());
            paySlip.setBonus(paySlipDto.getBonus());
            paySlip.setDeductions(paySlipDto.getDeductions());
            paySlip.setTotalPaid(paySlip.getTotalPaid());
            paySlip.setStatus(paySlipDto.getStatus());
            paySlip.setEmployee(employee);
            PaySlip savePaySlip = paySlipRepository.save(paySlip);
            if (savePaySlip.getStatus() == paySlipStatus.Validated || savePaySlip.getStatus() == paySlipStatus.Paid) {
                EmailDetails emailDetails = EmailDetails.builder()
                        .ReciverEmail(paySlip.getEmployee().getEmail())
                        .EmailSubject("Payroll Bulletin  " + savePaySlip.getStatus() + "\n")
                        .EmailBody("Your payroll bulletin for the period" + savePaySlip.getPayrollDate() + " %s has been processed successfully.  \n" +
                                "You can view and download it from your personal portal.")
                        .build();
                notificationService.sendEmail(emailDetails);
            }
            return StandardResponse.builder()
                    .code("200")
                    .status("success")
                    .data(PaySlipMapper.mapToPayrollDto(savePaySlip))
                    .build();
        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("error : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public StandardResponse DeletePayroll(Long id) {
        Optional<PaySlip> payroll = paySlipRepository.findById(id);
        if (payroll.isEmpty()) {
            return StandardResponse.builder()
                    .code("403")
                    .status("Payroll Not Found ")
                    .build();
        }
        PaySlip getPaySlip = payroll.get();
        paySlipRepository.delete(getPaySlip);
        return StandardResponse.builder()
                .code("200")
                .status("Payroll Deleted Successfully")
                .build();
    }


    @Override
    public StandardResponse FindPayrollByEmployeeId(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            return StandardResponse.builder()
                    .code("403")
                    .status("employee not found")
                    .build();
        }
        Employee getEmployee = employee.get();
        List<PaySlip> paySlips = paySlipRepository.findByEmployeeId(getEmployee.getId());
        List<PaySlipDto> payrollsDto = paySlips.stream().map(paySlip -> PaySlipMapper.mapToPayrollDto(paySlip)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("success")
                .data(payrollsDto)
                .build();
    }
}
