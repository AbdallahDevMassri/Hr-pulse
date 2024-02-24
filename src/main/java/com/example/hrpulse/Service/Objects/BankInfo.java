package com.example.hrpulse.Service.Objects;
import javax.persistence.*;
@Entity
@Table(name = "BankInfo")
public class BankInfo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "bankName")
        private String bankName;

        @Column(name = "accountNumber")
        private String accountNumber;
    public BankInfo(String bankName, String accountNumber) {
        // Initialize bank attributes here
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
