package com.muqing.service.impl;

import com.muqing.entity.Account;
import com.muqing.dao.AccountDao;
import com.muqing.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao dao;

    @Override
    public int add(Account account) {
        return dao.add(account);
    }

    @Override
    public int update(Account account) {
        return dao.update(account);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public Account findAccountById(int id) {
        return dao.queryAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
        return dao.queryAccountList();
    }
}
