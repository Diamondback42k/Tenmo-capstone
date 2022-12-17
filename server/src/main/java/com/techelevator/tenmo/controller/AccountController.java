package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    @Autowired
    TransferDAO transferDao;
    @Autowired
    AccountDAO dao;
    @Autowired
    UserDao userDao;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/get-users", method = RequestMethod.GET)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/get-accounts", method = RequestMethod.GET)
    public List<Account> findAccounts() {
        return dao.getAccounts();
    }


    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/get-account", method = RequestMethod.GET)
    public Account getAccount(Principal principal) {

        int userID = userDao.findIdByUsername(principal.getName());

        Account foundAccount = dao.findAccountByUserId(userID);

        return foundAccount;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/get-balance", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal) {


        int userID = userDao.findIdByUsername(principal.getName());

        BigDecimal balance = dao.getBalance(userID);

        return balance;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/transfer-create", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody Transfer transfer, Principal principal) {

        int senderID = userDao.findIdByUsername(principal.getName());
        int accountID = dao.accountIdByUserId(senderID);
        transfer.setUserIDSender(accountID);

        return transferDao.createTransfer(transfer);

    }


    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/transfer-update", method = RequestMethod.PUT)
    public Boolean transferUpdate(@RequestBody Transfer transfer, Principal principal) {
        BigDecimal newBalance;
        int senderID = userDao.findIdByUsername(principal.getName());//this retrieves the userID for the logged in user using the username
        int accountID = dao.accountIdByUserId(senderID); //this takes the userID ^^ and finds the accountID
        transfer.setUserIDSender(accountID);
        newBalance = dao.getBalance(senderID).subtract(transfer.getAmount());
        // (sender's account balance) - (transfer amount) ------- result = (1000.00) - (250.00).....750.00 BigDecimal ex.)
        if (newBalance.compareTo(dao.getBalance(senderID)) >= 0) { // if the difference between 'newBalance' compared to 'sender's balance' is 0.00, return true!
            return true;
        } else {
            return false;
        }


    }
}
