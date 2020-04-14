package com.ifun.component;

import com.alibaba.fastjson.JSON;
import com.ifun.dto.AccessTokenDTO;
import com.ifun.dto.UserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

/**
 * Create by iFun on 2020/03/30
 */
@Component
public class GithubProvider {

    private static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .addHeader("Accept","application/json")
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            return JSON.parseObject(str).get("access_token").toString();
        } catch (Exception e) {
            System.out.println("error:登陆--获取AccessToken异常");
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO getUser(String accessToker) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToker)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            UserDTO userDTO = JSON.parseObject(str, UserDTO.class);//fastjson可以自动把下划线标识映射到驼峰
            return userDTO;
        } catch (Exception e) {
            System.out.println("error:登陆--获取用户信息异常异常");
            e.printStackTrace();
        }
        return null;
    }
}
