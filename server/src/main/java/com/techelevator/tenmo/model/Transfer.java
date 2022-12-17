package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
//    @Min(value = 0, message = "value must be greater than 0.")
    private BigDecimal amount;
//    @NotBlank(message = "field cannot be equal to userIDSender.")
    private int userIDReceiver;
//    @NotBlank(message = "field cannot be empty")
    private int userIDSender;
//    @NotBlank(message = "field cannot be duplicate")
    private int transferID;

    public Transfer(BigDecimal amount, int userIDReceiver, int userIDSender,int transferID){
        this.amount = amount;
        this.userIDReceiver = userIDReceiver;
        this.userIDSender = userIDSender;
        this.transferID = transferID;
    }

    public Transfer(){

    }

    public int getUserIDReceiver() {
        return userIDReceiver;
    }

    public void setUserIDReceiver(int userIDReceiver) {
        this.userIDReceiver = userIDReceiver;
    }

    public int getUserIDSender() {
        return this.userIDSender;
    }

    public void setUserIDSender(int userIDSender) {
        this.userIDSender = userIDSender;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTransferID(int transfer_id) {
        return transferID;
    }

    public void setTransferID(int transferID) {
        this.transferID = transferID;
    }
}
