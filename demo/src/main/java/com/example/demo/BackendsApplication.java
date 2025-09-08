package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@SpringBootApplication(scanBasePackages = {
        "com.example.demo",
        "Controller",
        "Services",
        "entity",
        "repositories",
        "security"
})
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "entity")
public class BackendsApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.example.demo.BackendsApplication.class, args);
    }

}