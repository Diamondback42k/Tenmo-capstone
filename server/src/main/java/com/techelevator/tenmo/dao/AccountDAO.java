package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    List<Account> getAccounts();

    BigDecimal getBalance(int accountID);

    Account findAccountByUserId(int userId);

    int accountIdByUserId (int userID);

    Account getAccount(int accountId);


}
