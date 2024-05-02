package com.zhangying.myspring.test.bean;

import com.zhangying.myspring.stereotype.Component;

import java.util.Random;

/**
 * @className: AutoBeanUserService
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 20:54
 */
@Component("autoBeanUserService")
public class AutoBeanUserService{
    private String token;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳";
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
