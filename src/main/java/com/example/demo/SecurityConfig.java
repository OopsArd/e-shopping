package com.example.demo;

import com.example.demo.models.Accounts;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.NoSuchElementException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AccountService accountService;


    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean UserDetailsService trả về thông tin đăng nhập
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            try {
                System.out.println("username duoc nhap: " + username);
                Accounts accounts = accountService.findByUsername(username);
                System.out.println(accounts.toString());
                if (accounts == null) {
                    throw new UsernameNotFoundException(username + " not found");
                }

                return User.withUsername(accounts.getUsername())
                        .password(getPasswordEncoder().encode(accounts.getPassword()))
                        .roles(accounts.getRole())
                        .build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + " not found");
            }
        };
    }


    // Cấu hình AuthenticationManager thông qua AuthenticationConfiguration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // SecurityFilterChain để cấu hình bảo mật cho ứng dụng
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/order/**").authenticated()
                .requestMatchers("/admin/**").hasAnyRole("Staff", "Director")
                .requestMatchers("/rest/authorities").hasRole("Director")
                .anyRequest().permitAll()
        );

        http.formLogin(form -> form
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success", false)
                .failureUrl("/security/login/error")
        );

        http.rememberMe(rememberMe -> rememberMe
                .tokenValiditySeconds(86400)
        );

        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/security/unauthorized")
        );

        http.logout(logout -> logout
                .logoutUrl("/security/logout")
                .logoutSuccessUrl("/security/logout/success")
        );

        return http.build();
    }
}
