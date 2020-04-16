package com.ifun.mapper;

import com.ifun.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Create by iFun on 2020/03/31
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (ACCOUNT_ID,NAME,BIO,AVATAR_URL,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{accountId},#{name},#{bio},#{avatarUrl},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

    @Update("update user set NAME=#{name},BIO=#{bio},AVATAR_URL=#{avatarUrl},TOKEN=#{token},GMT_MODIFIED=#{gmtModified} where ID=#{id}")
    void updateUser(User user);

    @Select("select * from user where TOKEN = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where ACCOUNT_ID = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
}
