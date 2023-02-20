package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.RegistrationUser;

import java.security.Principal;
import java.util.List;


@Controller
//@RequestMapping(value = "users")
public class UserController {

    private final RegistrationUser registrationUser;
    private final UserRepository userRepository;

    @Autowired
    public UserController(RegistrationUser registrationUser, UserRepository userRepository) {
        this.registrationUser = registrationUser;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String helloRage() {
        return "hello";
    }

    @GetMapping("/user")
    public String printWelcome(ModelMap model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/registration")
    public String regPage(@ModelAttribute("user") User user) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute("user") @Validated User user) {
        registrationUser.registration(user);
        return "redirect:/user";
    }

    @GetMapping("/home")
    public String homePage(Model model, Principal principal){
        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        return "homepage";
    }

    @GetMapping(value = "/{id}/update")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userRepository.findById(id));
        return "update";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/user";
    }

    @DeleteMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);
        return "redirect:/user";
    }
}
