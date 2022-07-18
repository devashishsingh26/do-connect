package com.example.wipro.Service;

import com.example.wipro.Entity.Signup;
import com.example.wipro.Exception.UserExistsException;
import com.example.wipro.Repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl {

    @Autowired
    private SignupRepository signupRepository;

    public Signup createUser(Signup signup) throws UserExistsException {
        if(signupRepository.existsByUsername(signup.getUsername()))
            throw new UserExistsException();
        return signupRepository.save(signup);
    }

}
