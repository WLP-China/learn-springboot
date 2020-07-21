package com.muqing.dao;

import com.muqing.model.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Create by iFun on 2020/07/20
 */
@Mapper
public interface DepartmentDao {

    @Select("select * from t_department t where t.id = #{id}")
    Department getById(Long id);

    @Delete("delete from t_department where id = #{id}")
    int delete(Long id);

    int update(Department department);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_department(name, info, sort, isTop, img, createTime, updateTime) values(#{name}, #{info}, #{sort}, #{isTop}, #{img}, now(), now())")
    int save(Department department);

    int count(@Param("params") Map<String, Object> params);

    List<Department> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                    @Param("limit") Integer limit);

    List<Department> listAll();
}
