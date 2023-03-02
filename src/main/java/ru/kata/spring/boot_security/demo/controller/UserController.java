package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String helloPage() {
        return "redirect:/login";
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

    @PatchMapping(value = "/admin/update/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/admin/user";
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin/user";
    }
}
