package com.muqing.dao;

import com.muqing.model.Doctor;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Create by iFun on 2020/07/21
 */
@Mapper
public interface DoctorDao {

    @Select("select * from t_doctor t where t.id = #{id}")
    Doctor getById(Long id);

    @Delete("delete from t_doctor where id = #{id}")
    int delete(Long id);

    int update(Doctor doctor);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_doctor(name, photo, professionalTitle, deparimentId, departmentName, major, info,sort, createTime, updateTime) values(#{name},#{photo},#{professionalTitle},#{deparimentId},#{departmentName},#{major},#{info},#{sort}, now(), now())")
    int save(Doctor doctor);

    int count(@Param("params") Map<String, Object> params);

    List<Doctor> list(@Param("params") Map<String, Object> params,
                       @Param("offset") Integer offset,
                       @Param("limit") Integer limit);
}
