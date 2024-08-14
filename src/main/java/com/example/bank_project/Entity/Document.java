package com.example.bank_project.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "documents")
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_type", nullable = false)
    private String documentType;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    // Связь с клиентом
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;


}

