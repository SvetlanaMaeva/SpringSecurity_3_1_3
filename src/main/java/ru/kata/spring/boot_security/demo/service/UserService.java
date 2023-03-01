package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    List<User> findAll();
    Optional<User> findById(Long id);
    <S extends User> User save(User entity);
    void editUser(User updateUser);
    User getById(Long id);
    void delete(User entity);

    Collection<Role> getRoles();
}
