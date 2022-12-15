package com.example.lastlabcs;

import com.example.lastlabcs.entity.Role;
import com.example.lastlabcs.entity.User;
import com.example.lastlabcs.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class LastLabCsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LastLabCsApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER", ""));
            userService.saveRole(new Role(null, "ROLE_ADMIN", ""));

            userService.saveUser(new User(null, "Johnny Depp", "johnny", userService.getEncodedPassword("1234"), new HashSet<>()));
            userService.saveUser(new User(null, "Stefano Beresteano", "stefaniii", userService.getEncodedPassword("hello123"), new HashSet<>()));
            userService.saveUser(new User(null, "Catalino Ronaldinho", "cr7", userService.getEncodedPassword("siuu7"), new HashSet<>()));
            userService.saveUser(new User(null, "Lionel Pepsi", "pepsi10", userService.getEncodedPassword("password"), new HashSet<>()));

            userService.addRoleToUser("cr7", "ROLE_ADMIN");
            userService.addRoleToUser("stefaniii", "ROLE_USER");
            userService.addRoleToUser("johnny", "ROLE_USER");
            userService.addRoleToUser("pepsi10", "ROLE_USER");
        };
    }

}
