package org.kata.sgcib;

import org.junit.jupiter.api.Test;
import org.kata.sgcib.model.Account;
import org.kata.sgcib.service.AccountService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class BankingIntegrationTest {

    @Test
    void testFullScenario() {
        Account account = new Account();
        AccountService service = new AccountService(account);

        service.deposit(500);
        service.withdraw(200);
        service.deposit(300);

        assertEquals(600, account.getBalance(), 0.001);
        assertEquals(3, account.getTransactions().size());

        String statement = service.getAccountStatement();
        System.out.println(statement);
        assertTrue(statement.contains("SAVE"));
        assertTrue(statement.contains("WITHDRAW"));
    }
}
