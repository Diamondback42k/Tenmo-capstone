package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDAO {

    List<Transfer> getTransfers();

    Transfer getTransfer(int transferId);

    Transfer createTransfer (Transfer transfer);


    void depositAccount(int userIDReceiver, BigDecimal transferAmount);

    void withdrawAccount(int userIDSender, BigDecimal transferAmount);
}
