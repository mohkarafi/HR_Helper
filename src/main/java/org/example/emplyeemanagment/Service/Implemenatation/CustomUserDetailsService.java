package org.example.emplyeemanagment.Service.Implemenatation;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.UserAccount;
import org.example.emplyeemanagment.Repository.UserAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserAccountRepository userAccountRepository;
    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<UserAccount> userAccount = userAccountRepository.findByUsername(username);
            if (userAccount.isEmpty()) {
                throw new UsernameNotFoundException("User not found");
            }
            UserAccount user = userAccount.get();
            return User.builder().username(user.getUsername()).password(user.getPassword()).roles("USER").build();
        }

}
