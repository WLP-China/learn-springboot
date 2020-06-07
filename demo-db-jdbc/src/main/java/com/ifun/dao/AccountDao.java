package com.muqing.dao;

import com.muqing.entity.Account;

import java.util.List;

public interface AccountDao {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account queryAccountById(int id);

    List<Account> queryAccountList();
}
