package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JdbcTransferDAOTests extends BaseDaoTests {

    private JdbcTransferDao sut;
    private JdbcAccountDAO accountSut;
    @Before
    public void setup() {
        sut = new JdbcTransferDao(dataSource);
        accountSut = new JdbcAccountDAO(dataSource);
    }

    @Test
    public void getTransfers() {
        int expected = 2;
        Account account = accountSut.findAccountByUserId();
        List<Transfer> transfers = sut.getTransfers(accountSut.); //need to find a way to call up the account id, because it's required for a List

        Assert.assertEquals(2, transfers.size());


    }

    @Test
    public void getTransfer() {
    }

    @Test
    public void receiverId() {
    }

    @Test
    public void createTransfer() {
    }

    @Test
    public void depositAccount() {
    }

    @Test
    public void withdrawAccount() {
    }












}
