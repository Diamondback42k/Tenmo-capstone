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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class JdbcTransferDAOTests extends BaseDaoTests {

    private JdbcTransferDao sut;
    private JdbcAccountDAO accountSut;

    int expectedTransferId = 3001;
    int expectedReceiverAccountId = 2002;
    BigDecimal expectedTransferAmount = BigDecimal.valueOf(500.00).setScale(2, RoundingMode.HALF_DOWN);

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
        Transfer actualTransfer = sut.getTransfer(3001);

        Assert.assertEquals(expectedReceiverAccountId, actualTransfer.getaccountIDReceiver());
        Assert.assertEquals(expectedTransferId, actualTransfer.getTransferID(3001));
        Assert.assertEquals(expectedTransferAmount, actualTransfer.getAmount());
    }

    @Test
    public void receiverId() {
        int expectedReceiverId = 2002;
        Assert.assertEquals(expectedReceiverId, sut.receiverId(3001));
    }

    @Test
    public void createTransfer() {
        int expectedTransferId = 3001;
        Transfer actualTestTransfer = sut.getTransfer(3001);

        Transfer createdTransfer = sut.createTransfer(actualTestTransfer);

        int actualId = createdTransfer.getTransferID(3001);

        Assert.assertEquals(expectedTransferId, actualId);

    }
    
    }
    













