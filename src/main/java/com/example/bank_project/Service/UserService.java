package com.example.bank_project.Service;

import com.example.bank_project.Entity.*;
import com.example.bank_project.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserService {


    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private AccountRepo accountRepo;

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
        clientRepo.save(client);


        Account account = new Account();
        account.generateAccountNumber();
        account.setAccountType();
        account.setCreatedDate();
        account.setBalance(BigDecimal.ZERO); // баланс нулевой изначально
        account.setCurrency();
        account.setClient(client);// связь между клиентом и аккаунтом



        Card card = new Card();
        card.generateCardNumber();
        card.generateCardType();
        card.generateCvv();
        card.setAccount(account);// связь между аккаунтом и картой
        card.setStatus("Active");



        accountRepo.save(account);
        cardRepo.save(card);

    }

}


