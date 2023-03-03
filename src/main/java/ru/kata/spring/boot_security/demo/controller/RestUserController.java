package ru.kata.spring.boot_security.demo.controller;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class RestUserController {

    private final UserService userService;


    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/users")
    public List<User> showUserList() {
        return userService.findAll();
    }

    @GetMapping("/usersAll")
    public List<User> showUserListTest() {
        return userService.findAll();
    }

//    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/users/save")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/users/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.delete(userService.getById(id));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping ("/users/update/{id}")
    public void updateUserById(@RequestBody User user) {
        userService.editUser(user);
    }
}
