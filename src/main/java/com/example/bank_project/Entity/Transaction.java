package com.example.bank_project.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "description")
    private String description;

    // Связь с отправителем
    @ManyToOne
    @JoinColumn(name = "sender_account_id", nullable = false)
    private Account senderAccount;

    // Связь с получателем
    @ManyToOne
    @JoinColumn(name = "recipient_account_id", nullable = false)
    private Account recipientAccount;


}

