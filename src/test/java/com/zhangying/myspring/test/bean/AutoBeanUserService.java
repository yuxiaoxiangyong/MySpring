package com.zhangying.myspring.test.bean;

import com.zhangying.myspring.beans.factory.annotation.Autowired;
import com.zhangying.myspring.beans.factory.annotation.Value;
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

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.get("10001") + ",token:"+token;
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
