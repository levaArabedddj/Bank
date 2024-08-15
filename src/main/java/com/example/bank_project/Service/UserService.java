package com.example.bank_project.Service;

import com.example.bank_project.Entity.Users;
import com.example.bank_project.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String gmail, String rawPassword) {
        Users user = new Users();
        user.setGmail(gmail);
        user.setPassword(passwordEncoder.encode(rawPassword));
        usersRepo.save(user);
    }
}
