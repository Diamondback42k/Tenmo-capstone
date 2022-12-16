package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Username;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    List<Account> getAccounts();

    BigDecimal getBalance(int userID);

    Account findAccountByUserId(int userId);

    int accountIdByUserId (int userID);

    int receiverId(int accountId );

    Account getAccount(int accountId);

    Boolean depositAccount(int userIDReceiver, BigDecimal transferAmount);

    Boolean withdrawAccount(int userIDSender, BigDecimal transferAmount);

}
