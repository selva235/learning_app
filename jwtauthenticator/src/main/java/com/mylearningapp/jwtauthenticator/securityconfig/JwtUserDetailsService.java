package com.mylearningapp.jwtauthenticator.securityconfig;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mylearningapp.jwtauthenticator.entity.UserData;
import com.mylearningapp.jwtauthenticator.repository.UserDataRepo;

@Component
public class JwtUserDetailsService implements UserDetailsService  {

	@Autowired
    private UserDataRepo userDataRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = userDataRepo.findByUsername(username);
        System.out.println("userData:"+ userData);
        if (userData != null) {
            List<String> mappedAuthorities = Arrays.asList(userData.getRole().split(","));
            // if not using role based authentication, we can pass empty List instead of mappedAuthorities
            User user = new User(username, new BCryptPasswordEncoder().encode(userData.getPassword()), mappedAuthorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            return user;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
