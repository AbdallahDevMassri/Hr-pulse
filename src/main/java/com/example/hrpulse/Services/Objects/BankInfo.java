package com.example.hrpulse.Services.Objects;

import javax.persistence.*;

@Entity
@Table(name = "bank_info")
public class BankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "bankSneefCode")
    private int bankSneefCode;
    @Column(name = "bankNumber")
    private int bankNumber;
    @Column(name = "account_number")
    private String accountNumber;

    public BankInfo() {
        // Default no-argument constructor required by Hibernate
    }

    public BankInfo(String bankName, int bankSneefCode, int bankNumber, String accountNumber) {

        this.bankSneefCode = bankSneefCode;
        this.bankNumber = bankNumber;
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public int getBankSneefCode() {
        return bankSneefCode;
    }

    public void setBankSneefCode(int bankSneefCode) {
        this.bankSneefCode = bankSneefCode;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(int bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
