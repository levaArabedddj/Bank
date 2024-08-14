package com.example.bank_project.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "created_date", nullable = false)
    private String createdDate;

    // Связь с клиентом
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Связь с транзакциями
    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "recipientAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> incomingTransactions;

    // Связь с картами
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Card> cards;


}

