package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/accounts")
public class AccountController {

        @Autowired
        AccountDAO dao;

        @PreAuthorize("hasRole('ADMIN')")
        @RequestMapping(method = RequestMethod.GET)
        public List<Account> getAllAccounts(){
                return dao.getAccounts();
                }

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(path = "/{id}", method = RequestMethod.GET)
        public Account get(@Valid @PathVariable int accountId){
                Account account = dao.getAccount(accountId);
                if(account == null){
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Not Found");
                }
                return account;
        }

}
