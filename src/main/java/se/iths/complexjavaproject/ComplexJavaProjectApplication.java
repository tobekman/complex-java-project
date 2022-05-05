package se.iths.complexjavaproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.iths.complexjavaproject.entity.*;
import se.iths.complexjavaproject.repository.CategoryRepository;
import se.iths.complexjavaproject.repository.ItemRepository;
import se.iths.complexjavaproject.repository.RoleRepository;
import se.iths.complexjavaproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ComplexJavaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplexJavaProjectApplication.class, args);

    }

    @Bean
    public CommandLineRunner setUpRoles(RoleRepository roleRepository,
                                        UserRepository userRepository,
                                        ItemRepository itemRepository,
                                        CategoryRepository categoryRepository,
                                        BCryptPasswordEncoder passwordEncoder) {
        return (args) -> {

            List<Role> roles = (List<Role>) roleRepository.findAll();

            if(roles.isEmpty()) {
                roleRepository.save(new Role("ROLE_ADMIN"));
                roleRepository.save(new Role("ROLE_USER"));

                Address address1 = new Address("StenSaxPåsegatan 19", "89942", "Storstaden");
                Address address2 = new Address("Norra pastejkökstorget 123", "12334", "Småstaden");
                User user = new User("user", passwordEncoder.encode("password"), "user@hotmail.com", "Örgen Eriksson");
                User admin = new User("admin", passwordEncoder.encode("password"), "admin@hotmail.com", "Lotta Lotto");
                user.setAddress(address1);
                admin.setAddress(address2);

                userRepository.save(user);
                userRepository.save(admin);

                user.addRole(new Role(2L, "ROLE_USER"));
                admin.addRole(new Role(2L, "ROLE_USER"));
                admin.addRole(new Role(1L, "ROLE_ADMIN"));

                userRepository.save(user);
                userRepository.save(admin);

                Category category1 = new Category("Verktyg");
                Category category2 = new Category("Möbler");
                Category category3 = new Category("Maskiner");

                categoryRepository.save(category1);
                categoryRepository.save(category2);
                categoryRepository.save(category3);

                Item item1 = new Item("Hammare",  39.90);
                Item item2 = new Item("Stjärnskruvmejsel",  99.90);
                Item item3 = new Item("Japansk dragsåg",  299);
                Item item4 = new Item("Hörnsoffa",  17995);
                Item item5 = new Item("Bord",  6995);
                Item item6 = new Item("Stol",  295);
                Item item7 = new Item("Multislip",  1299);
                Item item8 = new Item("Multisåg",  899);
                Item item9 = new Item("Slagborrmaskin",  199);
                Item item10 = new Item("Gräsklippare",  3499);

                item1.addCategory(category1);
                item2.addCategory(category1);
                item3.addCategory(category1);
                item4.addCategory(category2);
                item5.addCategory(category2);
                item6.addCategory(category2);
                item7.addCategory(category3);
                item8.addCategory(category3);
                item9.addCategory(category3);
                item10.addCategory(category3);

                itemRepository.save(item1);
                itemRepository.save(item2);
                itemRepository.save(item3);
                itemRepository.save(item4);
                itemRepository.save(item5);
                itemRepository.save(item6);
                itemRepository.save(item7);
                itemRepository.save(item8);
                itemRepository.save(item9);
                itemRepository.save(item10);


            }














        };
    }

}
