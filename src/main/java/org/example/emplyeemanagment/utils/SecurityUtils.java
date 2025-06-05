package org.example.emplyeemanagment.utils;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Repository.UserAccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityUtils {
    private UserAccountRepository userAccountRepository;

    public Boolean isOwner(Long id) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println(username);
        return userAccountRepository.isOwner(username, id);
    }

}
