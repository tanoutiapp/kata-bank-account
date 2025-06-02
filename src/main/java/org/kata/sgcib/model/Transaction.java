package org.kata.sgcib.model;

import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime dateTime;
    private double amount;
    private Operation operation;


    public Transaction(LocalDateTime dateTime, double amount, Operation operation) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTransactionRecap() {
        return String.format("%s | %s | %.2f", dateTime, operation, amount);
    }


}
