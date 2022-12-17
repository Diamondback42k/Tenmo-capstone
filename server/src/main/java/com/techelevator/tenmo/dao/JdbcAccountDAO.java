package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDAO implements AccountDAO { //this will have our 'SQL' statements and methods

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        }

    @Override
    public List<Account> getAccounts() {
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
    public BigDecimal getBalance(int userID) {
        String sql = "SELECT balance FROM account WHERE user_id = ?;";
        BigDecimal returningBalance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userID);

            if(returningBalance != null){
                return returningBalance;
            }
                return null;
    }

    @Override
    public Account findAccountByUserId(int userId) {
        String sql = "SELECT * FROM account WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);

        Account account = null;
        if(result.next()){
            account = mapRowsToUser(result);
        }
        return account;
    }

    @Override
    public int accountIdByUserId(int userID){
        String sql = "SELECT account_id FROM account WHERE user_id = ?;";
        Integer accountId;
        try{
            accountId = jdbcTemplate.queryForObject(sql,Integer.class, userID);
        } catch (DataAccessException e) {
            return 0;
        }

        return accountId;
    }

    @Override
    public int receiverId(int accountId){
        String sql = "SELECT receiver_account_id FROM transfer WHERE account_id = ?;";
        Integer receivingId;
        try{
            receivingId = jdbcTemplate.queryForObject(sql, Integer.class, accountId);
        } catch(DataAccessException e) {
            return 0;
        }

        return receivingId;
}

    @Override
    public Account getAccount(int accountId) {
        String sql = "SELECT * FROM account where Account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);

        Account account = null;
        if(result.next()){
            account = mapRowsToUser(result);
        }
        return account;
    }



    private Account mapRowsToUser(SqlRowSet rowset) {
        Account account = new Account();
        account.setUserId(rowset.getInt("user_id"));
        account.setAccountId(rowset.getInt("account_id"));
        account.setBalance(rowset.getBigDecimal("balance"));

        return account;
    }

}
