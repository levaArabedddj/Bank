package com.example.bank_project.Repository;

import com.example.bank_project.Entity.Client;
import com.example.bank_project.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document, Long> {
    boolean existsByClient(Client client);
}
