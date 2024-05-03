package com.zhangying.myspring.test.bean;

import com.zhangying.myspring.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @className: HusbandMother
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/3 15:17
 */
public class HusbandMother implements FactoryBean<IMother> {

    @Override
    public IMother getObject() throws Exception {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IMother.class}, (proxy, method, args) -> "婚后媳妇妈妈的职责被婆婆代理了！" + method.getName());
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
