package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collections;

@Component
public class AuthProviderImp implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    @Autowired
    public AuthProviderImp(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        User userDetails = (User) userDetailsService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();

        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Неверные учетные данные пользователя");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getRoles());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
