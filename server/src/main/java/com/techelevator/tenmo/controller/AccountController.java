package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

        @Autowired
        AccountDAO dao;
        @Autowired
        UserDao userDao;

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(path = "/accounts/get-users", method = RequestMethod.GET)
        public List<User> findAll(){
                return userDao.findAll();
                }

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(path = "/get-account", method = RequestMethod.GET)
        public Account getAccount(Principal principal){

              int userID  = userDao.findIdByUsername(principal.getName());

                Account foundAccount = dao.findAccountByUserId(userID);

                return foundAccount;
        }

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(path = "/get-balance", method = RequestMethod.GET)
        public BigDecimal getBalance(Principal principal) {

//                userDao.findIdByUsername(principal.getName());
                int userID = userDao.findIdByUsername(principal.getName());

                BigDecimal balance = dao.getBalance(userID);

                return balance;
        }

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(path = "/transfer-create", method = RequestMethod.POST)
        public Account createTransfer(){
                return null;
        }
//              The Above method is the first of two methods needed for the transfer:
        //      1.) create a POST method that create and inserts the data so our user can have a LIST of choices to choose from
        //      2.) create an update method that updates the account balances between the sender and receiver
        //      *Remember the rules for the capstone*


}
