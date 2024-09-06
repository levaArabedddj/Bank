package com.example.bank_project.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
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


    // Метод для генерации уникального номера аккаунта (12-значное число)
    public void generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            accountNumber.append(random.nextInt(10));
        }
        this.accountNumber = accountNumber.toString();
    }

    // Метод для установки типа аккаунта (Физическая карта)
    public void setAccountType() {
        this.accountType = "Физическая карта";
    }

    public void setCurrency(){
        this.currency = "UAH";
    }

    // Метод для установки текущей даты в качестве даты создания аккаунта
    public void setCreatedDate() {
        this.createdDate = LocalDate.now().toString(); // Устанавливаем текущую дату в формате
    }

}

