package com.ifun.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //表明是一个映射的实体类
public class Account {

    @Id
    @GeneratedValue //字段自动生成
    private int id;
    private String name;
    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }    }

/** 主键生成策略

JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.
	TABLE：使用一个特定的数据库表格来保存主键。
	SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
	IDENTITY：主键由数据库自动生成（主要是自动增长型）
	AUTO：主键由程序控制（如果不指定主键生成策略，默认为AUTO）

*/