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

    Account getAccount(int accountId);

    Boolean depositAccount(Username username, BigDecimal transferAmount);

    Boolean withdrawAccount(Username username, BigDecimal transferAmount);

}
