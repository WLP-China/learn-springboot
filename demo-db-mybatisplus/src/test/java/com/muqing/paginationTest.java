package com.muqing;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muqing.entity.User;
import com.muqing.mapper.UserMapper;
import com.muqing.model.MyPage;
import com.muqing.model.ParamSome;
import com.muqing.model.UserChildren;
import com.muqing.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Create by iFun on 2020/08/30
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class paginationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void lambdaPagination() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, Wrappers.<User>lambdaQuery().ge(User::getAge, 20).orderByAsc(User::getAge));

        System.out.println(page.getTotal());
//        page.getRecords().forEach(System.out::println);
        print(page.getRecords());
    }

    @Test
    public void tests1() {
        log.error("----------------------------------baseMapper 自带分页-------------------------------------------------------");
        Page<User> page = new Page<>(1, 5);
        page.addOrder(OrderItem.asc("age"));
        Page<User> userIPage = userMapper.selectPage(page, Wrappers.<User>lambdaQuery().eq(User::getAge, 20).like(User::getName, "Jack"));
        log.error("总条数 -------------> {}", userIPage.getTotal());
        log.error("当前页数 -------------> {}", userIPage.getCurrent());
        log.error("当前每页显示数 -------------> {}", userIPage.getSize());
        print(userIPage.getRecords());

        log.error("----------------------------------json 正反序列化-------------------------------------------------------");
        String json = JSON.toJSONString(page);
        log.info("json ----------> {}", json);
        Page<User> page1 = JSON.parseObject(json, new TypeReference<Page<User>>() {
        });
        print(page1.getRecords());

        log.error("----------------------------------自定义 XML 分页-------------------------------------------------------");
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("Jack");
        ParamSome paramSome = new ParamSome(20, "Jack");
        MyPage<User> userMyPage = userMapper.mySelectPage(myPage, paramSome);
        log.error("总条数 -------------> {}", userMyPage.getTotal());
        log.error("当前页数 -------------> {}", userMyPage.getCurrent());
        log.error("当前每页显示数 -------------> {}", userMyPage.getSize());
    }

    @Test
    public void tests2() {
        /* 下面的 left join 不会对 count 进行优化,因为 where 条件里有 join 的表的条件 */
        MyPage<UserChildren> myPage = new MyPage<>(1, 5);
        myPage.setSelectInt(18).setSelectStr("Jack");
        MyPage<UserChildren> userChildrenMyPage = userMapper.userChildrenPage(myPage);
        List<UserChildren> records = userChildrenMyPage.getRecords();
        records.forEach(System.out::println);

        /* 下面的 left join 会对 count 进行优化,因为 where 条件里没有 join 的表的条件 */
        myPage = new MyPage<UserChildren>(1, 5).setSelectInt(18);
        userChildrenMyPage = userMapper.userChildrenPage(myPage);
        records = userChildrenMyPage.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    public void testMyPageMap() {
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("Jack");
        userMapper.mySelectPageMap(myPage, Maps.newHashMap("name", "%a"));
        myPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void testMap() {
        userMapper.mySelectMap(Maps.newHashMap("name", "%a")).forEach(System.out::println);
    }

    @Test
    public void myPage() {
        MyPage<User> page = new MyPage<>(1, 5);
        page.setName("a");
        userMapper.myPageSelect(page).forEach(System.out::println);
    }

    @Test
    public void iPageTest() {
        IPage<User> page = new Page<User>(1, 5) {
            private String name = "%";

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        };

        List<User> list = userMapper.iPageSelect(page);
        System.out.println("list.size=" + list.size());
        System.out.println("page.total=" + page.getTotal());
    }

    @Test
    public void rowBoundsTest() {
        RowBounds rowBounds = new RowBounds(0, 5);
        List<User> list = userMapper.rowBoundList(rowBounds, Maps.newHashMap("name", "%"));
        System.out.println("list.size=" + list.size());
    }

    @Test
    public void selectAndGroupBy() {
        LambdaQueryWrapper<User> lq = new LambdaQueryWrapper<>();
        lq.select(User::getAge).groupBy(User::getAge);
        for (User user : userMapper.selectList(lq)) {
            System.out.println(user.getAge());
        }
    }

    @Autowired
    IUserService userService;

    @Test
    public void lambdaPageTest() {
        LambdaQueryChainWrapper<User> wrapper2 = userService.lambdaQuery();
        wrapper2.like(User::getName, "a");
        userService.page(new Page<>(1, 10), wrapper2.getWrapper()).getRecords().forEach(System.out::print);
    }

    @Test
    public void test() {
        userService.lambdaQuery().like(User::getName, "a").list().forEach(System.out::println);

        Page page = userService.lambdaQuery().like(User::getName, "a").page(new Page<>(1, 10));
        page.getRecords().forEach(System.out::println);
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }
}
