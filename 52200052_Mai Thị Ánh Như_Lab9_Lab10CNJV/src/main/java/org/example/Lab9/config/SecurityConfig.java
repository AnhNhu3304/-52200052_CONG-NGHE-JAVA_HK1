package org.example.Lab9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.web.SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF nếu cần (dành cho API REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/products/**", "/api/account/**").permitAll() // Cho phép truy cập tự do vào /api/products và /api/account
                        .anyRequest().authenticated() // Yêu cầu xác thực với các endpoint khác
                )
                .formLogin(form -> form.disable()) // Vô hiệu hóa trang login mặc định
                .httpBasic(httpBasic -> {}); // Sử dụng xác thực HTTP Basic nếu cần

        return http.build();
    }
}
