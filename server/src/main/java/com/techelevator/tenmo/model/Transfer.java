package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Transfer {
    @Min(value = 0, message = "value must be greater than 0.")
    private BigDecimal amount;
    @NotBlank(message = "field cannot be equal to userIDSender.")
    private int userIDReceiver;
    @NotBlank(message = "field cannot be empty")
    private int userIDSender;
    @NotBlank(message = "field cannot be duplicate")
    private int transferID;

    public Transfer(BigDecimal amount, int userIDReceiver, int userIDSender,int transferID){
        this.amount = BigDecimal.ZERO;
        this. userIDReceiver = userIDReceiver;
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
        return userIDSender;
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

    public int getTransferID(int transferId) {
        return transferID;
    }

}
