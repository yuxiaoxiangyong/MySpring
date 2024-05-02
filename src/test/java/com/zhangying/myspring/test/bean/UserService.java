package com.zhangying.myspring.test.bean;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.*;
import com.zhangying.myspring.context.ApplicationContext;
import com.zhangying.myspring.context.ApplicationContextAware;
import jdk.nashorn.internal.runtime.Context;

/**
 * @className: UserService
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 15:18
 */
public class UserService implements InitializingBean, DisposableBean, ApplicationContextAware, BeanFactoryAware, BeanNameAware,BeanClassLoaderAware, IUserService {

    private String name;
    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    private IUserDao iUserDao;
    //private ApplicationContext applicationContext;

    //private BeanFactory beanFactory;

    public void queryUserInfo(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("queryUserInfo被调用了");
        //System.out.println(iUserDao.queryUserName("10001") + company);
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

    // Aware接口

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader:" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory: " + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanName: " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContext:" + applicationContext);
    }
}
