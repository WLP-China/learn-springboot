package com.ifun.component;

import com.alibaba.fastjson.JSON;
import com.ifun.dto.AccessTokerDTO;
import com.ifun.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

/**
 * Create by iFun on 2020/03/30
 */
@Component
public class GithubProvider {

    private static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");


    public String getAccessToken(AccessTokerDTO accessTokerDTO) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokerDTO), mediaType);
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

    public GithubUser getUser(String accessToker) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToker)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            System.out.println(str);
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            System.out.println("error:登陆--获取用户信息异常异常");
            e.printStackTrace();
        }
        return null;
    }
}
