package com.example.bank_project.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Table(name = "cards")
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "status", nullable = false)
    private String status;

    // Связь с счетом
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Метод для установки даты истечения на 2 года вперед
    public void generateExpirationDate() {
        this.expirationDate = LocalDate.now().plusYears(2); // Текущая дата + 2 года
    }

    // Генерация 16-значного номера карты
    public void generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10)); // Генерируем случайное число от 0 до 9
        }
        this.cardNumber = cardNumber.toString();
    }

    // Генерация типа карты (например, Visa, MasterCard)
    public void generateCardType() {
        String[] cardTypes = {"Visa", "MasterCard"};
        Random random = new Random();
        this.cardType = cardTypes[random.nextInt(cardTypes.length)]; // Случайный выбор типа карты
    }

    // Генерация 3-значного CVV кода
    public void generateCvv() {
        Random random = new Random();
        int cvvCode = 100 + random.nextInt(900); // Случайное число от 100 до 999
        this.cvv = String.valueOf(cvvCode);
    }

}

