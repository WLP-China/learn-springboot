package com.ifun.service;

import com.ifun.entity.Account;

import java.util.List;

public interface AccountService {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
