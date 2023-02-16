package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), roleToCollections(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> roleToCollections(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
