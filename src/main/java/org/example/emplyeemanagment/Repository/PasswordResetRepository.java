package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken , Long> {
}
