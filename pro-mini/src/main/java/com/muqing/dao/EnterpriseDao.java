package com.muqing.dao;

import com.muqing.model.Enterprise;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Create by iFun on 2020/06/28
 */
@Mapper
public interface EnterpriseDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_enterprise(eName, eInfo, creditCode, addr, telephone, status, createTime, updateTime) values (#{eName}, #{eInfo},#{creditCode},#{addr},#{telephone},#{status},now(), now())")
    int save(Enterprise enterprise);

    @Select("select * from t_enterprise t where t.id = #{id}")
    Enterprise getById(Long id);

    @Delete("delete from t_enterprise where id = #{id}")
    int delete(Long id);

    int update(Enterprise enterprise);

    Integer count(@Param("params") Map<String, Object> params);

    List<Enterprise> list(@Param("params") Map<String, Object> params,
                          @Param("offset") Integer offset,
                          @Param("limit") Integer limit);

    @Select("select * from t_enterprise t where status = #{status}")
    List<Enterprise> listByStatus(int status);
}
