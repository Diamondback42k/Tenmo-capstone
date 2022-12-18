package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class JdbcAccountDAOTests extends BaseDaoTests{

    //We need to create mock data so we don't use our actual database
    //
    //
    private static final Account Account_1 = new Account(2003,1003, BigDecimal.valueOf(1000.00));
    private static final Account Account_2 = new Account(2004,1004, BigDecimal.valueOf(700.00));


    private Account testAccount;

    private JdbcAccountDAO sut;

    @Before
    public void setup() {
        sut = new JdbcAccountDAO(dataSource);
        testAccount = new Account(2020, 1010, BigDecimal.valueOf(5000.00));
    }

    @Test
    public void getAccount_returns_correct_Account(){
        Account account = sut.getAccount(2001);
        assertAccountsMatch(Account_1, account);

        account = sut.getAccount(2002);
        assertAccountsMatch(Account_2, account);
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

        assertAccountsMatch(Account_1, accounts.get(0));
        assertAccountsMatch(Account_2, accounts.get(1));


    }

    @Test
    public void getBalance_returns_correct_balance_with_accountID(){

        Account account = sut.getAccount(2001);

        BigDecimal actualBalance = sut.getBalance(account.getAccountId());
        BigDecimal expectedBalance = sut.getBalance(Account_1.getAccountId());

        Assert.assertEquals(expectedBalance, actualBalance);

    }

private void assertAccountsMatch(Account expected, Account actual){
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
}

//private void assertBalancesMatch(BigDecimal expected, BigDecimal actual){
//        Assert.assertEquals(expected.ge)
//}

}
