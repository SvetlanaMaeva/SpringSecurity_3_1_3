package ru.kata.spring.boot_security.demo.controller;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;
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

    @GetMapping("/users/role")
    public ResponseEntity<Collection<Role>> getAllRoles(){
        return new ResponseEntity<>(userService.getRoles(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping("/users/save")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/users/home")
    public User getUserById(Principal principal) {
        return userService.findByUsername(principal.getName());
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.delete(userService.getById(id));
    }

    @PatchMapping (value = "/users/update")
    public ResponseEntity<HttpStatus> updateUserById(@RequestBody User user) {
        userService.editUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
