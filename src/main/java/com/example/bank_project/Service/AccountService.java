package com.example.bank_project.Service;

import com.example.bank_project.Entity.Account;
import com.example.bank_project.Entity.Client;
import com.example.bank_project.Repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;


    public void saveAccount(Account account) {
        accountRepo.save(account);
    }


    public Account findAccountByClient(Client client) {
        return accountRepo.findByClient(client);
    }
}
