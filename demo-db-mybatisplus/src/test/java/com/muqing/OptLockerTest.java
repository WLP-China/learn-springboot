package com.muqing;

import com.muqing.entity.User;
import com.muqing.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 乐观锁测试
 * Create by iFun on 2020/08/30
 */
@SpringBootTest
public class OptLockerTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testUpdateByIdSucc() {
        User user = new User();
        user.setAge(18);
        user.setEmail("test@baomidou.com");
        user.setName("optlocker");
        user.setVersion(1);
        userMapper.insert(user);
        Long id = user.getId();

        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setAge(19);
        userUpdate.setVersion(1);
        assertThat(userMapper.updateById(userUpdate)).isEqualTo(1);
        assertThat(userUpdate.getVersion()).isEqualTo(2);
    }

    @Test
    public void testUpdateByIdSuccFromDb() {
        User user = userMapper.selectById(1);
        int oldVersion = user.getVersion();
        int i = userMapper.updateById(user);
        assertThat(i).isEqualTo(1);
        assertThat(oldVersion + 1).isEqualTo(user.getVersion());
    }

    @Test
    public void testUpdateByIdFail() {
        User user = new User();
        user.setAge(18);
        user.setEmail("test@baomidou.com");
        user.setName("optlocker");
        user.setVersion(1);
        userMapper.insert(user);
        Long id = user.getId();

        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setAge(19);
        userUpdate.setVersion(0);
        Assert.assertEquals("Should update failed due to incorrect version(actually 1, but 0 passed in)", 0, userMapper.updateById(userUpdate));
    }

    @Test
    public void testUpdateByIdSuccWithNoVersion() {
        User user = new User();
        user.setAge(18);
        user.setEmail("test@baomidou.com");
        user.setName("optlocker");
        user.setVersion(1);
        userMapper.insert(user);
        Long id = user.getId();

        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setAge(19);
        userUpdate.setVersion(null);
        Assert.assertEquals("Should update success as no version passed in", 1, userMapper.updateById(userUpdate));
        User updated = userMapper.selectById(id);
        Assert.assertEquals("Version not changed", 1, updated.getVersion().intValue());
        Assert.assertEquals("Age updated", 19, updated.getAge().intValue());
    }

}
