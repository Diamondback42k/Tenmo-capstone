package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface transferInterface {

    List<Transfer> getTransfers();

    Transfer getTransfer(int transferID);

    boolean create(int receiverAccountID, BigDecimal amount);

}
