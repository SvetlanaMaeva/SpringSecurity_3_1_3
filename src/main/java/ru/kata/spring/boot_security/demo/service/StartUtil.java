package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class StartUtil implements CommandLineRunner {
    private final UserService userService;
    private final Role admin = new Role("ROLE_ADMIN");
    private final Role user = new Role("ROLE_USER");

    @Autowired
    public StartUtil(UserService userService) {
        this.userService = userService;
    }
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Collection<Role> roleAdmin = new ArrayList<>();
        Collection<Role> roleUser = new ArrayList<>();
//        Collection<Role> roleUser2 = new ArrayList<>();
//        Collection<Role> roleUserAndAdmin = new ArrayList<>();
        roleAdmin.add(admin);
        roleUser.add(user);
//        roleUser2.add(user);
//        roleUserAndAdmin.add(admin);
//        roleUserAndAdmin.add(user);


        userService.save(new User("admin", "admin@gmail.com", "admin", roleAdmin));
        userService.save(new User("user", "user@gmail.com", "user", roleUser));
//        userService.save(new User("Айзек Азимов", "korvin@gmail.com", "isaak", roleUserAndAdmin));
//        userService.save(new User("Роджер Желязны", "user@gmail.com", "amber", roleUser2));
//        userService.save(new User("Роберт Хайнлайн", "dan@gmail.com", "door", roleUser));

    }
}