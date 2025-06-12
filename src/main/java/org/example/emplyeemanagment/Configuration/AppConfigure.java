package org.example.emplyeemanagment.Configuration;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Service.Implemenatation.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class AppConfigure {
    private CustomUserDetailsService userDetailsService;
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    /*     auth.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    auth.requestMatchers( HttpMethod.POST, "/auth/signup").permitAll();
                    auth.requestMatchers(HttpMethod.GET , "/Employee/employeeByEmail").hasAnyRole("ADMIN" , "USER");
                    auth.requestMatchers(HttpMethod.GET , "/Employee/employees").hasAnyRole("ADMIN" , "USER");
                    auth.requestMatchers(HttpMethod.DELETE, "/Employee/delete/").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/Employee/AddLeave/{id}").hasAnyRole( "USER", "ADMIN");
                    auth.requestMatchers(HttpMethod.GET, "/Employee/findOne/{id}").hasAnyRole( "USER", "ADMIN");
                })*/
                    auth.anyRequest().permitAll();
                })
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager(http));
        return http.build();
    }



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }


}
