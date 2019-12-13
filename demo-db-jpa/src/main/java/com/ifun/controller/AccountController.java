package com.ifun.controller;

import com.ifun.dao.AccountDao;
import com.ifun.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountDao dao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getList() {
        return dao.findAll();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable("id") int id) {
//        return dao.findOne(id);
        return dao.findById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "name") String name,
                      @RequestParam(value = "money") double money) {
        Account account = new Account();
        account.setName(name);
        account.setMoney(money);
        Account account1 = dao.save(account);
        return account1.toString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable("id") int id,
                         @RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "money", required = true) double money) {
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setMoney(money);
        Account account1 = dao.saveAndFlush(account);
        return account1.toString();
    }
}
