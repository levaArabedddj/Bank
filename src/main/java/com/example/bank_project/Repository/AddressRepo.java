package com.example.bank_project.Repository;

import com.example.bank_project.Entity.Address;
import com.example.bank_project.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
    boolean existsByClient(Client client);

}
