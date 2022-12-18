package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Transfer {
    @DecimalMin(value = "1", message = "value must be greater than 0.")
    private BigDecimal amount;
    //@NotBlank(message = "field cannot be equal to accountIDSender.")
    private int accountIDReceiver;
    //@NotBlank(message = "field cannot be empty")
    private int accountIDSender;
    //@NotBlank(message = "field cannot be duplicate")
    private int transferID;

    public Transfer(BigDecimal amount, int accountIDReceiver, int accountIDSender, int transferID){
        this.amount = amount;
        this.accountIDReceiver = accountIDReceiver;
        this.accountIDSender = accountIDSender;
        this.transferID = transferID;
    }

    public Transfer(){

    }

    public int getaccountIDReceiver() {
        return accountIDReceiver;
    }

    public void setaccountIDReceiver(int accountIDReceiver) {
        this.accountIDReceiver = accountIDReceiver;
    }

    public int getaccountIDSender() {
        return this.accountIDSender;
    }

    public void setaccountIDSender(int accountIDSender) {
        this.accountIDSender = accountIDSender;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTransferID(int transferID) {
        return transferID ;
    }

    public void setTransferID(int transferID) {
        this.transferID = transferID;
    }
}
