package com.muqing.dao;

import com.muqing.dto.OrderVO;
import com.muqing.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Create by iFun on 2020/06/30
 */
@Mapper
public interface OrderDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_order(buyer_enterprise, buyer_enterprise_id,  proname, position, nun_order, concrete_additive, concrete_type, concrete_level, pumping_type, sendTime, is_finance, pay_type, status, createTime, updateTime) " +
            "values (#{buyerEnterprise}, #{buyerEnterpriseId},  #{proname}, #{position}, #{nunOrder}, #{concreteAdditive}, #{concreteType}, #{concreteLevel}, #{pumpingType}, #{sendTime}, #{isFinance}, #{payType}, #{status}, now(), now())")
    int save(Order order);

    @Select("select * from t_order t where t.id = #{id}")
    Order getById(Long id);

    @Delete("delete from t_order where id = #{id}")
    int delete(Long id);

    int update(Order order);

    Integer count(@Param("params") Map<String, Object> params);

    List<OrderVO> list(@Param("params") Map<String, Object> params,
                       @Param("offset") Integer offset,
                       @Param("limit") Integer limit);
}
