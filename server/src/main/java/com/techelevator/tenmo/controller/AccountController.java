package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer-create", method = RequestMethod.POST)
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer, Principal principal) {

        int senderID = userDao.findIdByUsername(principal.getName()); //this finds the user id
        int accountID = dao.accountIdByUserId(senderID); //this finds the account id
        transfer.setaccountIDSender(accountID);
        if (dao.getBalance(accountID).compareTo(transfer.getAmount()) >= 0) {
            return transferDao.createTransfer(transfer);
        } else {
            return null;
        }

    }
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/transfers", method = RequestMethod.GET)
    public List<Transfer> getTransfers(Principal principal){
        int senderID = userDao.findIdByUsername(principal.getName());
        int accountID = dao.accountIdByUserId(senderID);

        return transferDao.getTransfers(accountID);

    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/transfer/{transferID}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int transferID){

        return transferDao.getTransfer(transferID);
    }

}