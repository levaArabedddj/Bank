package com.example.bank_project.Entity;


import jakarta.persistence.*;
import lombok.Data;
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

}

