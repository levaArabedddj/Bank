package com.example.bank_project.Repository;

import com.example.bank_project.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
