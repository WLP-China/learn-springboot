package com.muqing.dao;

import com.muqing.model.OrderSeller;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Create by iFun on 2020/07/23
 */
@Mapper
public interface OrderSellerDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_order_seller(orderId,no,num_send,p_jianzhan,p_driver, createTime, updateTime) values (#{orderId},#{no},#{numSend},#{pJianzhan},#{pDriver},now(), now())")
    int save(OrderSeller orderSeller);

//    @Select("select * from t_order_seller t where t.id = #{id}")
//    OrderSeller getById(Long id);

    @Delete("delete from t_order_seller where id = #{id}")
    int delete(Long id);

//    int update(Order order);

    @Select("select count(1) from t_order_seller t where t.orderId = #{orderId}")
    Integer countByOrderId(Long orderId);

    @Select("select * from t_order_seller t where t.orderId = #{orderId}")
    List<OrderSeller> listByOrderId(Long orderId);
}
