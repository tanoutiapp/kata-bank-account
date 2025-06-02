package org.kata.sgcib.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.sgcib.exception.AccountOperationException;
import org.kata.sgcib.model.Account;
import org.kata.sgcib.model.Transaction;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private Account account;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        account = new Account();
        accountService = new AccountService(account);
    }

    @Test
    void testDepositShouldAddToBalanceAndRecordTransaction() {
        accountService.deposit(100.0);
        assertEquals(100.0, account.getBalance());
        assertEquals(1, account.getTransactions().size());
        Transaction transaction = account.getTransactions().get(0);
        assertEquals(100.0, transaction.getAmount());
        assertEquals("SAVE", transaction.getTransactionRecap().split("\\|")[1].trim());
    }

    @Test
    void testDepositNegativeAmountThrowsException() {
        assertThrows(AccountOperationException.class, () -> accountService.deposit(-50));
    }

    @Test
    void testWithdrawShouldSubtractFromBalanceAndRecordTransaction() {
        accountService.deposit(200.0);
        accountService.withdraw(50.0);
        assertEquals(150.0, account.getBalance());
        Transaction transaction = account.getTransactions().get(1);
        assertEquals(-50.0, transaction.getAmount());
        assertEquals("WITHDRAW", transaction.getTransactionRecap().split("\\|")[1].trim());
    }

    @Test
    void testWithdrawNegativeAmountThrowsException() {
        assertThrows(AccountOperationException.class, () -> accountService.withdraw(-20));
    }

    @Test
    void testWithdrawMoreThanBalanceThrowsException() {
        accountService.deposit(50);
        assertThrows(AccountOperationException.class, () -> accountService.withdraw(100));
    }

    @Test
    void testGetAccountStatementIncludesHeaderAndBalance() {
        accountService.deposit(100);
        accountService.withdraw(30);
        String statement = accountService.getAccountStatement();
        assertTrue(statement.contains("Date"));
        assertTrue(statement.contains("Current balance"));
    }
}

