package com.muqing.dao;

import com.muqing.model.ConcreteType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConcreteTypeDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_concrete_type(type, k, val, money, sort, createTime, updateTime) values(#{type}, #{k}, #{val}, #{money}, #{sort}, now(), now())")
    int save(ConcreteType concreteType);

    @Select("select * from t_concrete_type t where t.id = #{id}")
    ConcreteType getById(Long id);

    @Delete("delete from t_concrete_type where id = #{id}")
    int delete(Long id);

    int update(ConcreteType concreteType);


    int count(@Param("params") Map<String, Object> params);

    List<ConcreteType> list(@Param("params") Map<String, Object> params,
                            @Param("offset") Integer offset,
                            @Param("limit") Integer limit);

    @Select("select * from t_concrete_type t where t.type = #{type} and k = #{k}")
    ConcreteType getByTypeAndKey(@Param("type") String type, @Param("k") String k);

    @Select("select * from t_concrete_type t where t.type = #{type}")
    List<ConcreteType> listByType(String type);
}
