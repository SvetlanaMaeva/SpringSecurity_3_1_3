package ru.kata.spring.boot_security.demo.controller;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


@RestController
public class RestUserController {

    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api")
    public ResponseEntity<List<User>> showUserList() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/role")
    public ResponseEntity<Collection<Role>> getAllRoles(){
        return new ResponseEntity<>(userService.getRoles(), HttpStatus.OK);
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/api/save")
    public ResponseEntity<User>  saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping("/api/home")
    public ResponseEntity<User>  getUserById(Principal principal) {
        return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<HttpStatus>  deleteUserById(@PathVariable Long id) {
        userService.delete(userService.getById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping (value = "/api/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        System.out.println(user);
        userService.editUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
