package com.example.wipro.Controller;

import com.example.wipro.Entity.JwtResponse;
import com.example.wipro.Entity.Signup;
import com.example.wipro.Exception.UserNotFoundException;
import com.example.wipro.Repository.SignupRepository;
import com.example.wipro.Service.CustomUserDetailService;
import com.example.wipro.helper.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Signup login) throws Exception {
        Signup user = signupRepository.getByUsername(login.getUsername());
        String password = null;
        if (user==null)
            throw new UserNotFoundException();
        password = user.getPassword();
        if(!Objects.equals(login.getPassword(), password)) {
            return new ResponseEntity<>("Wrong credentials", HttpStatus.FORBIDDEN);
        }

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                    password));

        } catch (UsernameNotFoundException e) {
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(user.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token,user.getUsername(),user.getRole(),user.getId()));
    }
}
