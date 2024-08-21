package com.example.bank_project.Security;

import com.example.bank_project.Entity.Users;
import com.example.bank_project.Repository.UsersRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo users;
    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        logger.info("Attempting to load user by email: " + gmail);

        Optional<Users> optionalUser = users.findByGmail(gmail);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            logger.info("User found: " + user.getGmail());
            return new MyUserDetails(user);
        } else {
            logger.warn("User not found with email: " + gmail);
            throw new UsernameNotFoundException(gmail + " not such user ");
        }
    }
}
