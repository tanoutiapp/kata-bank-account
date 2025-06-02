package org.kata.sgcib.service;

import org.kata.sgcib.exception.AccountOperationException;
import org.kata.sgcib.model.Account;
import org.kata.sgcib.model.Operation;
import org.kata.sgcib.model.Transaction;

import java.time.LocalDateTime;

public class AccountService {
    private Account account;

    public AccountService(Account account) {
        this.account = account;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new AccountOperationException("Deposit must be positive");
        }
        account.setBalance(account.getBalance() + amount);
        Transaction transaction = new Transaction(LocalDateTime.now(), amount, Operation.SAVE);
        account.recordTransaction(transaction);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new AccountOperationException("Withdrawal must be positive");
        }
        if (amount > account.getBalance()) {
            throw new AccountOperationException("Insufficient funds: Cannot withdraw more than the current balance.");
        }
        account.setBalance(account.getBalance() - amount);
        Transaction transaction = new Transaction(LocalDateTime.now(), -amount, Operation.WITHDRAW);
        account.recordTransaction(transaction);
    }

    public String getAccountStatement() {
        StringBuilder builder = new StringBuilder();
        builder.append("Date       |     Amount |    Balance\n");
        builder.append("------------------------------------\n");
        for (Transaction transaction : account.getTransactions()) {
            builder.append(transaction.getTransactionRecap()).append("\n");
        }
        builder.append(String.format("Current balance: %.2f $", account.getBalance()));
        return builder.toString();
    }

}
