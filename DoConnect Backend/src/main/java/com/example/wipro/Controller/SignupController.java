package com.example.wipro.Controller;

import com.example.wipro.Entity.Signup;
import com.example.wipro.Exception.UserExistsException;
import com.example.wipro.Exception.UserNotFoundException;
import com.example.wipro.Service.SignupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class SignupController {

    @Autowired
    private SignupServiceImpl signup;

    @PostMapping("/signup")
    public Signup createUser(@RequestBody Signup signup) throws UserExistsException {
        return this.signup.createUser(signup);
    }


}
