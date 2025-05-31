package org.example.emplyeemanagment.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserAccount implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id"  )
    private Long Id ;
    @Column(nullable = true)
    private String username;
    @Column(nullable = true)
    private String password;
    private String role = "USER";
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id" , nullable = false , unique = true)
    private Employee employee;
}
