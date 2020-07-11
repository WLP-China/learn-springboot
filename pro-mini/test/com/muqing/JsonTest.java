package com.muqing;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.muqing.dto.Contact;
import org.junit.Test;

/**
 * Create by iFun on 2020/07/11
 */
public class JsonTest {

    //Java对象 转 json字符串
    @Test
    public void objToJson() {
        Contact contact = new Contact();
        contact.setName("张三");
        contact.setPhone("18811112222");

        String str = JSON.toJSONString(contact);
        System.out.println(str);
    }

    //json字符串 转 java对象
    @Test
    public void jsonToObj() {
        String str = "{'name':'张三','phone':'18811112222'}";

        //方法1
        Contact parse = JSON.parseObject(str, Contact.class);
        //方法2
//        Contact parse = JSON.parseObject(str, new TypeReference<Contact>() {});
        System.out.println(parse.getName() + " || " + parse.getPhone());
    }

}
