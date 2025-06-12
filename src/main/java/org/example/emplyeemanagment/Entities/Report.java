package org.example.emplyeemanagment.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.emplyeemanagment.Enums.ReportStatus;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "name", nullable = false)
    private String title ;
    @Column(name = "creted_date", nullable = false)
    private Date createdAt;
    private String content;
    private ReportStatus status ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intern")
    private Intern intern ;
}
