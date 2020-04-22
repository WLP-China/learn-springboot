package com.ifun.mbg.mapper;

import com.ifun.mbg.model.User;
import com.ifun.mbg.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithRowbounds(UserExample example, RowBounds rowBounds);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
/*
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
}*/