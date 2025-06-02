package org.kata.sgcib;


import org.kata.sgcib.model.Account;
import org.kata.sgcib.service.AccountService;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        AccountService accountService = new AccountService(account);

        accountService.deposit(1000);
        accountService.withdraw(700);
        accountService.deposit(500);

        System.out.println(account.getCurrentBalance());
        System.out.println(accountService.getAccountStatement());
        }
}