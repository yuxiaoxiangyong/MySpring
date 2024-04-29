package com.zhangying.myspring.test.bean;

/**
 * @className: UserService
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 15:18
 */
public class UserService {

    private String name;


    public void queryUserInfo(){
        System.out.println("查询用户信息");
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService(){

    }
}
