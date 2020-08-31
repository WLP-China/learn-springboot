package com.muqing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muqing.entity.User;
import com.muqing.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 18);//大于等于
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Jone");
        User user = userMapper.selectOne(wrapper); // 查询一个数据，出现多个结果使用List 或者 Map
        System.out.println(user);
    }

    @Test
    void test3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30); // 区间
        Integer count = userMapper.selectCount(wrapper);// 查询结果数
        System.out.println(count);
    }

    // 模糊查询
    @Test
    void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 左和右  t%
        wrapper
                .notLike("name", "Tom")
                .likeRight("email", "123456");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    // 模糊查询
    @Test
    void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id 在子查询中查出来
        wrapper.inSql("id", "select id from user where id<3");

        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");//排序

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
