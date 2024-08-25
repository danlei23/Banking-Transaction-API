package com.demo.banking.dto;

public class AccountDTO {
    private Long id;
    private String name;
    private double initialBalance;

    public AccountDTO() {}

    public AccountDTO(Long id, String name, double initialBalance) {
        this.id = id;
        this.name = name;
        this.initialBalance = initialBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }
}
