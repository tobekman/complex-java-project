package se.iths.complexjavaproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import se.iths.complexjavaproject.entity.Role;
import se.iths.complexjavaproject.repository.RoleRepository;

@SpringBootApplication
public class ComplexJavaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplexJavaProjectApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner setUpRoles(RoleRepository roleRepository) {
//        return (args) -> {
//            roleRepository.save(new Role("ROLE_ADMIN"));
//            roleRepository.save(new Role("ROLE_USER"));
//        };
//    }

}
