package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDAO implements AccountDAO { //this will have our 'SQL' statements and methods

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;}


    @Override
    public List<Account> findAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Account account = mapRowsToUser(results);
            accounts.add(account);

        }

        return accounts;
    }

    @Override
    public BigDecimal getBalance(BigDecimal balance) {
        return null;
    }

    @Override
    public int findAccountId(int accountId) {
        return 0;
    }

    @Override
    public int findAccountByUserId(int userId) {
        return 0;
    }

    private Account mapRowsToUser(SqlRowSet rowset) {
        Account account = new Account();
        account.setUser_id(Integer.parseInt("user_id"));
        account.setAccount_id(Integer.parseInt("account_id"));
        account.setBalance(BigDecimal.valueOf(Double.parseDouble("balance")));

        return account;
    }

}
