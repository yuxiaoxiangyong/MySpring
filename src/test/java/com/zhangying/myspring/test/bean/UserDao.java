package com.zhangying.myspring.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: UserDao
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 21:14
 */
public class UserDao {

    private static Map<String, String> database = new HashMap<>();


    public void initDataMethod(){
        System.out.println("执行：init-method");
        database.put("10001", "小傅哥");
        database.put("10002", "八杯水");
        database.put("10003", "阿毛");
    }


    public UserDao(){

    }


    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        database.clear();
    }


    public String get(String key){
        return database.get(key);
    }

}
