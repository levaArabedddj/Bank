package com.example.bank_project.Service;

import com.example.bank_project.DAO.AddressForm;
import com.example.bank_project.DAO.ContactForm;
import com.example.bank_project.DAO.DocumentForm;
import com.example.bank_project.Entity.*;
import com.example.bank_project.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(String email, String rawPassword, String firstName, String lastName) {
        Users user = new Users();
        user.setGmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("USER_ROLE");

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setUser(user); // Связь между пользователем и клиентом

        user.setClient(client);

        usersRepo.save(user);
        clientRepo.save(client); // Сохранить клиента после пользователя
    }

}


