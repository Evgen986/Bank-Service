package ru.maliutin.bankapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Сущность клиента.
 */
@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones;

    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Email> emails;
    @OneToOne(mappedBy = "client",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;
}
