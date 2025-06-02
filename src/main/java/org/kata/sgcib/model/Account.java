package org.kata.sgcib.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance = 0.0;
    private List<Transaction> transactions = new ArrayList();


    public void recordTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getCurrentBalance() {
        return String.format("your account contains %.2f $", balance);
    }
}
