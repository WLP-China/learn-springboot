package com.ifun.mapper;

import com.ifun.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Create by iFun on 2020/03/31
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (ACCOUNT_ID,NAME,NICKNAME,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{accountId},#{name},#{nickname},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);
}
