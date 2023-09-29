package com.example.hrpulse.Services.Objects;

import javax.persistence.*;

@Entity
@Table(name = "bank_info")
public class BankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_number")
    private String accountNumber;

    public BankInfo() {
        // Default no-argument constructor required by Hibernate
    }

    public BankInfo(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        // Initialize other bank attributes here as needed
    }

    // Getter and setter methods for bank attributes
    // ...
}
