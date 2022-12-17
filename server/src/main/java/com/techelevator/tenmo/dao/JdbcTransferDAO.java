package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.Username;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcTransferDao implements TransferDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    @Override
//    public Transfer senderFunction(){
//        String sql = "SELECT SUM(account.account_balance) - SUM(transfer.transfer_amount) " +
//                "FROM account" +
//                "JOIN transfer ON "
//
//    }


    @Override
    public List<Transfer> getTransfers() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public Transfer getTransfer(int transferID){
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferID);

        Transfer transfer = null;
        if(result.next()){
           transfer = mapRowToTransfer(result);
        }
        return transfer;

    }


    @Override
    public Transfer createTransfer(Transfer transfer) {

        String sql = "INSERT INTO transfer (account_id, receiver_account_id, transfer_amount) VALUES (?,?,?) RETURNING transfer_id";

        Integer newTransferId;
        try {

             newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getUserIDSender(), transfer.getUserIDReceiver(), transfer.getAmount());
             transfer.setTransferID(newTransferId);

             depositAccount(transfer.getUserIDReceiver(), transfer.getAmount());

             withdrawAccount(transfer.getUserIDSender(), transfer.getAmount());


             return getTransfer(newTransferId);

        }catch(DataAccessException e){
            return null;
        }
    }

    @Override
    public void depositAccount(int userIDReceiver, BigDecimal transferAmount) {
        String sql = "UPDATE account SET balance = balance + ? WHERE user_id = ?";
        jdbcTemplate.update(sql, transferAmount, userIDReceiver);



    }

    @Override
    public void withdrawAccount(int userIDSender, BigDecimal transferAmount) {
        String sql = "UPDATE account SET balance = balance - ? WHERE user_id = ?";
        jdbcTemplate.update(sql, transferAmount, userIDSender);

    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
            Transfer transfer = new Transfer();
            transfer.getTransferID(rs.getInt("transfer_id"));
            transfer.setUserIDSender(rs.getInt("account_id"));
            transfer.setUserIDReceiver(rs.getInt("receiver_account_id"));
            transfer.setAmount(rs.getBigDecimal("transfer_amount"));
            return transfer;
        }

    private Username mapRowToUsername(SqlRowSet rs) {
        Username username = new Username();
        username.setUsername(rs.getString("username"));
        return username;

    }

}
