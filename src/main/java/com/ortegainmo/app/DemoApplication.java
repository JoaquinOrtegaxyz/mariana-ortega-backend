package com.ortegainmo.app;

import com.ortegainmo.app.security.user.User;
import com.ortegainmo.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();

                admin.setEmail("mortegainmo@gmail.com");

                admin.setPassword(passwordEncoder.encode("Mariana123!"));


                userRepository.save(admin);

                User admin2 = new User();

                admin2.setEmail("alfredofarisanonec@gmail.com");

                admin2.setPassword(passwordEncoder.encode("Yamil123!"));
                userRepository.save(admin2);
            }
        };
    }
}
