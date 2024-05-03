package com.zhangying.myspring.test.bean;

/**
 * @className: Husband
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/3 15:13
 */
public class Husband {

    private Wife wife;

    public String queryWife(){
        return "Husband.wife";
    }


    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}