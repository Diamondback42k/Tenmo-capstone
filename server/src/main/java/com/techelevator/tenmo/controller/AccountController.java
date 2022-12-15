package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.JdbcAccountDAO;
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
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/accounts")
public class AccountController {

        @Autowired
        AccountDAO dao;
        UserDao userDao;
        private Account account;

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(method = RequestMethod.GET)
        public List<User> findAll(){
                return userDao.findAll();
                }

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(path = "/get-account", method = RequestMethod.POST)
        public void getAccount(@RequestBody int userId){

                System.out.println(dao.findAccountByUserId(userId));
        }

        @PreAuthorize("hasRole('USER')")
        @RequestMapping(path = "/get-balance", method = RequestMethod.GET )
        public void getBalance(Principal principal) {
                System.out.println(principal.getName());

        }




}
