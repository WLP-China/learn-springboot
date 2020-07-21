package com.muqing.dao;

import com.muqing.model.Nurse;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Create by iFun on 2020/07/21
 */
@Mapper
public interface NurseDao {

    @Select("select * from t_nurse t where t.id = #{id}")
    Nurse getById(Long id);

    @Delete("delete from t_nurse where id = #{id}")
    int delete(Long id);

    int update(Nurse nurse);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_nurse(name,photo,professionalTitle,info,sort, createTime, updateTime) values(#{name},#{photo},#{professionalTitle},#{info},#{sort}, now(), now())")
    int save(Nurse nurse);

    int count(@Param("params") Map<String, Object> params);

    List<Nurse> list(@Param("params") Map<String, Object> params,
                       @Param("offset") Integer offset,
                       @Param("limit") Integer limit);
}
