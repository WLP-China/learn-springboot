package com.muqing.component;

import com.alibaba.fastjson.JSON;
import com.muqing.dto.GiteeAccessTokenDTO;
import com.muqing.dto.UserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

/**
 * Create by iFun on 2020/04/01
 */
@Component
public class GiteeProvider {

    private static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    public String getAccessToken(GiteeAccessTokenDTO accessTokenDTO) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
//                .addHeader("Accept","application/json")
                .url("https://gitee.com/oauth/token?")
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

    //GithubUser中的信息再gitee中一样适用，此处暂用GithubUser
    public UserDTO getUser(String accessToker) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token=" + accessToker)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            UserDTO userDTO = JSON.parseObject(str, UserDTO.class);
            return userDTO;
        } catch (Exception e) {
            System.out.println("error:登陆--获取用户信息异常异常");
            e.printStackTrace();
        }
        return null;
    }
}
