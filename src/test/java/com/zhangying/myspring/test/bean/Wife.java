package com.zhangying.myspring.test.bean;

/**
 * @className: Wifr
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/3 15:13
 */
public class Wife{

    private Husband husband;
    private IMother mother; // 婆婆

    public String queryHusband() {
        return "Wife.husband、Mother.callMother：" + mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}