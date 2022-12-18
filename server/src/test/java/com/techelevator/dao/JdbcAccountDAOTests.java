package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class JdbcAccountDAOTests extends BaseDaoTests{

    private JdbcAccountDAO sut;

    @Before
    public void setup() {
        sut = new JdbcAccountDAO(dataSource);
    }

    @Test
    public void getAccount_returns_correct_Account(){
        int expectedId = 2001;
        BigDecimal expectedBalance = BigDecimal.valueOf(1000.00).setScale(2, RoundingMode.HALF_DOWN);
        int expectedUserId = 1001;
        Account account = sut.getAccount(2001);

        Assert.assertEquals(expectedId, account.getAccountId());
        Assert.assertEquals(expectedBalance, account.getBalance());
        Assert.assertEquals(expectedUserId, account.getUserId());
    }

    @Test
    public void getAccount_returns_null_when_id_not_found(){
        Account account = sut.getAccount(3000);
        Assert.assertNull(account);
    }

    @Test
    public void getAccounts_returns_all_Accounts(){
        List<Account> accounts = sut.getAccounts();
        Assert.assertEquals(2, accounts.size());

    }

    @Test
    public void getBalance_returns_correct_balance_with_accountID(){

        Account account = sut.getAccount(2001);
        BigDecimal actualBalance = sut.getBalance(account.getAccountId());
        BigDecimal expectedBalance = BigDecimal.valueOf(1000.00).setScale(2, RoundingMode.HALF_DOWN);

        Assert.assertEquals(expectedBalance, actualBalance);

    }

    @Test
    public void findAccountByUserId() {
        int expected = 1001;
        Account actual = sut.getAccount(2001);
        Assert.assertEquals(expected, actual.getUserId());

    }

    @Test
    public void accountIdByUserId() {
        int expected = 2001;
        Account actual = sut.getAccount(2001);
        Assert.assertEquals(expected, actual.getAccountId());
    }

    private void assertAccountsMatch(Account expected, Account actual){
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
}

}
