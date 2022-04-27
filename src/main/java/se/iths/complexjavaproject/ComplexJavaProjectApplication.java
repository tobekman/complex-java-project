package se.iths.complexjavaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@EntityScan(basePackages = {"entity"})
public class ComplexJavaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplexJavaProjectApplication.class, args);

    }

}
