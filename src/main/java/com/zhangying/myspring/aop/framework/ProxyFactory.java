package com.zhangying.myspring.aop.framework;

import com.zhangying.myspring.aop.AdvisedSupport;

/**
 * @className: ProxyFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 17:12
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }

}
