package com.ifun.dto;

/**
 * Create by iFun on 2020/03/30
 */
public class GithubUser {
    private Long id;
    private String login;//登录名
    private String name;//昵称
    private String bio;//描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
