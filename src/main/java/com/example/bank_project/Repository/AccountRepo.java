package com.example.bank_project.Repository;

import com.example.bank_project.Entity.Account;
import com.example.bank_project.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByClient(Client client);

}
