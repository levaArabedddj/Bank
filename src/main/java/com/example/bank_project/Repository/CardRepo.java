package com.example.bank_project.Repository;

import com.example.bank_project.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card, Long> {
}
