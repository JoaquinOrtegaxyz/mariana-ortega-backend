package com.ortegainmo.app;

import com.ortegainmo.app.security.user.User;
import com.ortegainmo.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.TimeZone;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String defaultEmail = "admin@inmobiliaria.com";
            if (userRepository.findByEmail(defaultEmail).isEmpty()) {
                User admin = User.builder()
                        .email(defaultEmail)
                        .password(passwordEncoder.encode("admin123"))
                        .build();
                userRepository.save(admin);
            }

            // También dejamos reseteado ortegainmo@gmail.com con la misma clave para que puedas entrar
            userRepository.findByEmail("ortegainmo@gmail.com").ifPresent(user -> {
                user.setPassword(passwordEncoder.encode("admin123"));
                userRepository.save(user);
            });

            System.out.println("==================================================");
            System.out.println("  USUARIOS ADMIN LISTOS PARA INICIAR SESIÓN:");
            System.out.println("  1) Email: admin@inmobiliaria.com | Password: admin123");
            System.out.println("  2) Email: ortegainmo@gmail.com   | Password: admin123");
            System.out.println("==================================================");
        };
    }

}