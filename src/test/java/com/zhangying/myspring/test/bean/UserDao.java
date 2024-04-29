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

    static {
        database.put("1", "洛杉矶湖人队");
        database.put("2", "洛杉矶快船队");
        database.put("3", "达拉斯独行侠队");
    }

    public UserDao(){

    }

    public String get(String key){
        return database.get(key);
    }

}
