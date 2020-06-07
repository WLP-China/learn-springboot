package com.muqing.dao;

import com.muqing.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据访问层，通过编写一个继承自 JpaRepository 的接口就能完成数据访问,其中包含了基本的单表查询的方法。
 *
 * 1.Account 是对象名，而不是具体的表名，
 * 2.Interger 是主键的类型，一般为Integer或者Long
 */
public interface AccountDao extends JpaRepository<Account,Integer> {
}
