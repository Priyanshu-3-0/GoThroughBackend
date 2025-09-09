package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.example.demo",
                "com.example.demo.Controllers",
                "com.example.demo.Services",
                "com.example.demo.repositories",
                "com.example.demo.entity",
                "com.example.demo.security"
        }
)
@EnableJpaRepositories(basePackages = "com.example.demo.repositories")
@EntityScan(basePackages = "com.example.demo.entity")
public class BackendsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendsApplication.class, args);
    }
}
