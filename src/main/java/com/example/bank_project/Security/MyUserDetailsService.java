package com.example.bank_project.Security;

import com.example.bank_project.Entity.Users;
import com.example.bank_project.Repository.UsersRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    private UsersRepo users;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        Optional<Users> user = users.findByGmail(gmail);
        return user.map(users -> {
            return new MyUserDetails(users);
        }).orElseThrow(() ->
                new UsernameNotFoundException(gmail + " not such user "));
    }
}
