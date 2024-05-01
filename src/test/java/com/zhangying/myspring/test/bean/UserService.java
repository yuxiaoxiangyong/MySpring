package com.zhangying.myspring.test.bean;

import com.zhangying.myspring.beans.factory.DisposableBean;
import com.zhangying.myspring.beans.factory.InitializingBean;

/**
 * @className: UserService
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 15:18
 */
public class UserService implements InitializingBean, DisposableBean {

    private String name;

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public void queryUserInfo(){
        System.out.println(uId + userDao);
    }

    public void queryUserInfo(String id){
        System.out.println(userDao.get(id) + "，公司：" + company + ", 地点" + location);
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService(){

    }


    @Override
    public void destroy() throws Exception {
        System.out.println("执行: UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行: UserService.afterPropertiesSet");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
