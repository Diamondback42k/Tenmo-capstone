package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Account;
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

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        }

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
    public BigDecimal getBalance(int accountId) {
        String sql = "SELECT balance FROM account WHERE account_id = ?;";
        BigDecimal returningBalance = jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId);

            if(returningBalance != null){
                return returningBalance;
            }
                return null;
    }


//
//    @RequestMapping( path = "/todos/{id}", method = RequestMethod.GET)
//    public Todo getById(@PathVariable int id) {
//        Todo result = dao.getTodoById(id);
//        if (result == null) {
//            // 404 Not found
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "To-do not found", null);
//        }
//        return result;
//    }

//    @Override
//    public Account findAccountByUserId(int userId) {
//        String sql = "SELECT * FROM account WHERE user_id = ?;";
//        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
//        try{
//            if(rowSet.next()){
//                return mapRowsToUser(rowSet);
//        }
//        }catch(Exception e){
//            System.out.println("Account not found: User ID invalid");
//        }
//
//    }

    private Account mapRowsToUser(SqlRowSet rowset) {
        Account account = new Account();
        account.setUser_id(Integer.parseInt("user_id"));
        account.setAccount_id(Integer.parseInt("account_id"));
        account.setBalance(BigDecimal.valueOf(Double.parseDouble("balance")));

        return account;
    }

}
