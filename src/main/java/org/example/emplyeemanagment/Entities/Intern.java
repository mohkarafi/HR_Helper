package org.example.emplyeemanagment.Entities;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false , length = 50)
    private String fullName ;
    @Column( nullable = false , length = 50)
    private String email ;
    @Column(nullable = false , length = 50)
    private String phone ;
    @Column(nullable = false , length = 50)
    private String school;
    @Column(nullable = false , length = 50)
    private Date startDate ;
    @Column(nullable = false , length = 50)
    private Date endDate ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor")
    private Employee supervisor  ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;
    @OneToMany(mappedBy = "intern")
    private List<Report> report;
}
