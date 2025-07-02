package org.example.emplyeemanagment.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "password_reset_token")
public class PasswordResetToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false )
    private Long id ;
    @Column(nullable = false , unique = true )
    private String token ;
    @OneToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private UserAccount userId ;
    @Column(nullable = false)
    private LocalDateTime expiryDate ;
}
