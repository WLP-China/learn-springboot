package com.ifun.mapper;

import com.ifun.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Create by iFun on 2020/03/31
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (ACCOUNT_ID,NAME,BIO,AVATAR_URL,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{accountId},#{name},#{bio},#{avatarUrl},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

    @Select("select * from user where TOKEN = #{token}")
    User findByToken(@Param("token") String token);
}
