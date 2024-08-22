package org.example.springjpahr.config;

import org.example.springjpahr.entity.Role;
import org.example.springjpahr.entity.User;
import org.example.springjpahr.service.RoleService;
import org.example.springjpahr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppStartUp implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()) {
            //Roles
            Role role = new Role();
            role.setName("Admin");
            Role role2 = new Role();
            role2.setName("User");
            roleService.insert(role);
            roleService.insert(role2);

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(role);

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role2);

            //users
            User user = new User();
            user.setUserName("Haneen");
            user.setPassword("han123");
            user.setRoles(adminRoles);

            User user2 = new User();
            user2.setUserName("Mohamed");
            user2.setPassword("moh123");
            user2.setRoles(userRoles);

            userService.insert(user);
            userService.insert(user2);
        }
    }
}