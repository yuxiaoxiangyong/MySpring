package com.zhangying.myspring.test.bean;

/**
 * @className: MyUserService
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 16:39
 */
public class MyUserService implements IUserService{

    private String token;

    @Override
    public void queryUserInfo() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("queryUserInfo被调用了" + ",token:" + token);
    }
}
