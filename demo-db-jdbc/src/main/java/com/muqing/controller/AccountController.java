package com.muqing.controller;

import com.muqing.entity.Account;
import com.muqing.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getList() {
//        return service.findAccountList();
        List<Account> list = jdbcTemplate.query("select * from account", new Object[]{}, new BeanPropertyRowMapper(Account.class));
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public Account getById(@PathVariable("id") int id) {
        return service.findAccountById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "name") String name,
                      @RequestParam(value = "money") double money) {
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        int t = service.add(account);
        if (t == 1) {
            return account.toString();
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable("id") int id,
                         @RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = " ", required = true) double money) {
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t = service.update(account);
        if (t == 1) {
            return account.toString();
        } else {
            return "fail";
        }
    }
}
