package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
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

    @Override
    public List<Transfer> getTransfers(int accountID) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountID);
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
    public int receiverId(int transferId){
        String sql = "SELECT receiver_account_id FROM transfer WHERE transfer_id = ?;";
        Integer receivingId;
        try{
            receivingId = jdbcTemplate.queryForObject(sql, Integer.class, transferId);
        } catch(DataAccessException e) {
            return 0;
        }

        return receivingId;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {

        String sql = "INSERT INTO transfer (account_id, receiver_account_id, transfer_amount) VALUES (?,?,?) RETURNING transfer_id";

        Integer newTransferId;

        try {

             newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getaccountIDSender(), transfer.getaccountIDReceiver(), transfer.getAmount());
             transfer.setTransferID(newTransferId);

            if(transfer.getaccountIDSender() != transfer.getaccountIDReceiver()){

                depositAccount(transfer.getaccountIDReceiver(), transfer.getAmount());
                withdrawAccount(transfer.getaccountIDSender(), transfer.getAmount());
                return transfer;

            } else {
                System.out.println("Change the receiving ID");
                return null;
            }
        }catch(DataAccessException e){
            return null;
        }

    }

    @Override
    public void depositAccount(int accountIDReceiver, BigDecimal amount) {
        String sql = "UPDATE account SET balance = balance + ? WHERE account_id = ?";
        jdbcTemplate.update(sql, amount, accountIDReceiver);

    }

    @Override
    public void withdrawAccount(int accountIDSender, BigDecimal amount) {
        String sql = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
        jdbcTemplate.update(sql, amount, accountIDSender);

    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
            Transfer transfer = new Transfer();
            transfer.getTransferID(rs.getInt("transfer_id"));
            transfer.setaccountIDSender(rs.getInt("account_id"));
            transfer.setaccountIDReceiver(rs.getInt("receiver_account_id"));
            transfer.setAmount(rs.getBigDecimal("transfer_amount"));
            return transfer;
        }

    private Username mapRowToUsername(SqlRowSet rs) {
        Username username = new Username();
        username.setUsername(rs.getString("username"));
        return username;

    }

}
