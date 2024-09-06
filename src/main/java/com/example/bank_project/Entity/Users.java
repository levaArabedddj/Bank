package com.example.bank_project.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "User_Bank")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String gmail;

    private String password;
    private String role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Client client;



    @Override
    public int hashCode() {
        return Objects.hash(id); // Не включаем коллекции или другие потенциально рекурсивные поля
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id);
    }
}

