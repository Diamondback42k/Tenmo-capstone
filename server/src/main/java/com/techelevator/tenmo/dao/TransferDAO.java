package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    List<Transfer> getTransfers();

    Transfer getTransfer(int transferId);

    Transfer createTransfer (Transfer newTransfer);


    Boolean depositAccount(int userIDReceiver, BigDecimal transferAmount);

    Boolean withdrawAccount(int userIDSender, BigDecimal transferAmount);
}
