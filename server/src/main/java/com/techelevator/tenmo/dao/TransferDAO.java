package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDAO {

    List<Transfer> getTransfers(int accountID);

    Transfer getTransfer(int transferId);

    int receiverId(int transferId);

    Transfer createTransfer (Transfer transfer);

    void depositAccount(int accountIDReceiver, BigDecimal transferAmount);

    void withdrawAccount(int accountIDSender, BigDecimal transferAmount);
}
