package com.example.demo.services;

import com.example.demo.dto.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.error_handler.APIErrorHandler;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // Loads a user's details given their userName.
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        return userOptional.map(user -> new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        }).orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }

    // Adds a new user to the repository and encrypting password before saving it.
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("User"));
        userRepository.save(user);
    }

    public boolean validateUser(LoginUser user){
        Optional<User> userFromDB = userRepository.findByUserName(user.getUserName());
        if(userFromDB.isEmpty()){
            throw new APIErrorHandler("User Not Found!", HttpStatus.NOT_FOUND);
        }
        return passwordEncoder.matches(user.getPassword(),userFromDB.get().getPassword());
    }
}
