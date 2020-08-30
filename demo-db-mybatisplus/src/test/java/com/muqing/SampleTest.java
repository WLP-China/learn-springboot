package com.muqing;

import com.muqing.entity.User;
import com.muqing.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<User> users = userMapper.selectList(null);

        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        user.setEmail("123456@qq.com");

        int i = userMapper.insert(user);//会主动生成id
        System.out.println(i);
        System.out.println(user);//id会自动回填
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(6L);
        user.setAge(20);
        int i = userMapper.updateById(user);//动态sql
        System.out.println(i);
    }

    //===== ===== ===== select ===== ===== =====
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByBatchIds() {
        HashMap<String, Object> map = new HashMap<>();
        // 自定义要查询
        map.put("name", "张三");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //===== ===== ===== 删除 ===== ===== =====
    @Test
    public void testDeleteById() {
        userMapper.deleteById(1L);
    }

    @Test
    public void testDeleteBatchId() {
        userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L));
    }

    @Test
    public void testDeleteMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        userMapper.deleteByMap(map);
    }
}
