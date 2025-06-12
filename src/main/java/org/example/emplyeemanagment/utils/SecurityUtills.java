package org.example.emplyeemanagment.utils;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Repository.UserAccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityUtills {
    private UserAccountRepository userAccountRepository;
    public boolean IsOwner(String usernmae , Long id){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         String usernmae1 = userDetails.getUsername();
         return  userAccountRepository.isOwner(usernmae, id);
    }
}
