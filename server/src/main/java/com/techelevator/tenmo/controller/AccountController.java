package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/accounts")
public class AccountController {

        @Autowired
        AccountDAO dao;

//        public AccountController() {
//                this.dao = new JdbcAccountDAO();
//
//        }


        @PreAuthorize("hasRole('USER')")
        @RequestMapping(path = "/{id}", method = RequestMethod.GET)
        public Account get(@PathVariable int accountId){
                Account account = dao.getAccount(accountId);
                return account;
        }

//
//    @RequestMapping( path = "/todos/{id}", method = RequestMethod.GET)
//    public Todo getById(@PathVariable int id) {
//        Todo result = dao.getTodoById(id);
//        if (result == null) {
//            // 404 Not found
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "To-do not found", null);
//        }
//        return result;
//    }
}
