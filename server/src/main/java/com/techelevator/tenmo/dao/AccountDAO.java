package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    List<Account> getAccounts();

    BigDecimal getBalance(int userID);

    Account findAccountByUserId(int userId);

    Account getAccount(int accountId);

}
