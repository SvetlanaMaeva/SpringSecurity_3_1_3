package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


@Controller
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String helloRage() {
        return "hello";
    }

    @GetMapping("/home")
    public String homePage(Model model, Principal principal){
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "homepage";
    }

    @GetMapping("/admin/user")
    public String printWelcome(ModelMap model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<User> users = userService.findAll();
        User newUser = new User();
        Collection<Role> roles = userService.getRoles();
//        User userId = userService.getById();
//        model.addAttribute("userId", userId);
        model.addAttribute("newUser", newUser);
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/admin/user")
    public String registrationUser(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/{id}/update")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "update";
    }

    @PatchMapping(value = "/admin/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/admin/user";
    }

    @DeleteMapping(value = "/admin/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin/user";
    }
}
