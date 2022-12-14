package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    List<Account> findAllAccounts();

    BigDecimal getBalance(BigDecimal balance);

    Account findAccountId(int accountId);

    int findAccountByUserId(int userId);

}
