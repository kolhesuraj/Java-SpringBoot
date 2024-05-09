package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class authentication  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Loads a user's details given their userName.
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        return userOptional.map(user -> {
            return new UserDetails() {
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
            };
            }).orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }

    // Adds a new user to the repository and encrypting password before saving it.
    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "user added successfully";
    }
}
