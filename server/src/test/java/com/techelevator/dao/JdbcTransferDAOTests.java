package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

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

        int actual = sut.getTransfers(2001).size();
        Assert.assertEquals(1, actual);
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
