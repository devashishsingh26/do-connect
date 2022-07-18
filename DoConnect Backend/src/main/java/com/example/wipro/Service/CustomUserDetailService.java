package com.example.wipro.Service;


import com.example.wipro.Config.UserDetailsImpl;
import com.example.wipro.Entity.Signup;
import com.example.wipro.Repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SignupRepository signupRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Signup user = signupRepository.getByUsername(username);

        String password = passwordEncoder.encode(user.getPassword());

        try {
            return new User(username,password,getAuthority(user));
        } catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("user not found");
        }

    }

    public Set<SimpleGrantedAuthority> getAuthority(Signup user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
        return authorities;
    }

}