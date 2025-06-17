package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.Payroll;
import org.example.emplyeemanagment.Enums.payrollStatus;
import org.example.emplyeemanagment.Mappers.PayrollMapper;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.PayrollRepository;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.NotificationService;
import org.example.emplyeemanagment.Service.PayrollService;
import org.example.emplyeemanagment.dtos.EmailDetails;
import org.example.emplyeemanagment.dtos.PayrollDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PayrollServiceImpl implements PayrollService {
    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;
    private final NotificationService notificationService;

    @Override
    public StandardResponse AddPayroll(PayrollDto payrollDto) {
        try {
            Payroll payroll = PayrollMapper.mapToPayroll(payrollDto);
            Employee employee = employeeRepository.findById(payrollDto.getEmployeeId()).orElseThrow(() -> new Exception("Employee not found"));
            payroll.setEmployee(employee);
            Payroll SavePayroll = payrollRepository.save(payroll);
            return StandardResponse.builder()
                    .code("200")
                    .status("success")
                    .data(PayrollMapper.mapToPayrollDto(SavePayroll))
                    .build();
        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("error : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public StandardResponse UpdatePayroll(Long id, PayrollDto payrollDto) {
        try {
            Payroll payroll = payrollRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payroll not found"));
            Employee employee = employeeRepository.findById(payroll.getEmployee().getId()).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
            payroll.setId(payrollDto.getId());
            payroll.setPayrollDate(payrollDto.getPayrollDate());
            payroll.setBaseSalary(payrollDto.getBaseSalary());
            payroll.setBonus(payrollDto.getBonus());
            payroll.setDeductions(payrollDto.getDeductions());
            payroll.setTotalPaid(payroll.getTotalPaid());
            payroll.setStatus(payrollDto.getStatus());
            payroll.setEmployee(employee);
            Payroll SavePayroll = payrollRepository.save(payroll);
            if(SavePayroll.getStatus()== payrollStatus.Validated || SavePayroll.getStatus()== payrollStatus.Paid){
                EmailDetails emailDetails = EmailDetails.builder()
                        .ReciverEmail(payroll.getEmployee().getEmail())
                        .EmailSubject("Payroll Bulletin  " + SavePayroll.getStatus()+"\n")
                        .EmailBody("Your payroll bulletin for the period" +SavePayroll.getPayrollDate()+ " %s has been processed successfully.  \n" +
                                "You can view and download it from your personal portal.")
                        .build();
                notificationService.sendEmail(emailDetails);
            }
            return StandardResponse.builder()
                    .code("200")
                    .status("success")
                    .data(PayrollMapper.mapToPayrollDto(SavePayroll))
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
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if (payroll.isEmpty()) {
            return StandardResponse.builder()
                    .code("403")
                    .status("Payroll Not Found ")
                    .build();
        }
        Payroll getPayroll = payroll.get();
        payrollRepository.delete(getPayroll);
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
        List<Payroll> payrolls = payrollRepository.findByEmployeeId(getEmployee.getId());
        List<PayrollDto> payrollsDto = payrolls.stream().map(payroll -> PayrollMapper.mapToPayrollDto(payroll)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("success")
                .data(payrollsDto)
                .build();
    }
}
