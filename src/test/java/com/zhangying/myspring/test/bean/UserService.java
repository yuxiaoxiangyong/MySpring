package com.zhangying.myspring.test.bean;

/**
 * @className: UserService
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 15:18
 */
public class UserService {

    private String name;

    private UserDao userDao;

    public void queryUserInfo(){
        System.out.println(name);
    }

    public void queryUserInfo(String id){
        System.out.println(userDao.get(id));
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService(){

    }
}
