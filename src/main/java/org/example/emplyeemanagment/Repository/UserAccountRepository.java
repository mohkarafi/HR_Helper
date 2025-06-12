package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);

  @Query("Select count(u)>0 from UserAccount u Where u.username = :username AND u.employeeID=:employeeId")
    public boolean isOwner(@Param("username")String username, @Param("employeeId")Long employeeId);

}
