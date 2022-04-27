package se.iths.complexjavaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ComplexJavaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplexJavaProjectApplication.class, args);
    }

}
