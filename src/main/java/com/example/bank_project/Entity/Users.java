package com.example.bank_project.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column(unique = true)
    private String gmail;

    private String password;
    private String role;

}
