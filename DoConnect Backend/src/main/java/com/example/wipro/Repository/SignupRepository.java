package com.example.wipro.Repository;

import com.example.wipro.Entity.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepository extends JpaRepository<Signup,Long> {
    boolean existsByUsername(String username);

    Signup getByUsername(String username);
}
