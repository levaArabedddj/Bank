package com.example.bank_project.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "sent_date", nullable = false)
    private LocalDateTime sentDate;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    // Связь с клиентом
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}

