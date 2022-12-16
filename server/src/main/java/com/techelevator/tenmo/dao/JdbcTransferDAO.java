package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcTransferDAO implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


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
    public Transfer create(int senderAccountID, int receiverAccountID) {

        String sql = "INSERT INTO transfer (account_id, receiver_account_id, transfer_amount) VALUES (?,?,?) RETURNING transfer_id";
        int newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, senderAccountID, receiverAccountID);

        return getTransfer(newTransferId);
    }



    private Transfer mapRowToTransfer(SqlRowSet rs) {
            Transfer transfer = new Transfer();
            transfer.getTransferID(rs.getInt("transfer_id"));
            transfer.setUserIDSender(rs.getInt("account_id"));
            transfer.setUserIDReceiver(rs.getInt("receiver_account_id"));
            transfer.setAmount(rs.getBigDecimal("transfer_amount"));
            return transfer;
        }



}
