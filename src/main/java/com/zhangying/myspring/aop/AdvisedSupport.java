package com.zhangying.myspring.aop;
import org.aopalliance.intercept.MethodInterceptor;
/**
 * @className: AdvisedSupport
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 15:09
 */
public class AdvisedSupport {

    private boolean proxyTargetClass = false;

    // 被代理的目标对象
    private TargetSource targetSource;


    // 方法拦截器
    private MethodInterceptor methodInterceptor;


    // 方法匹配器(检查目标方法是否符合通知条件)
    private MethodMatcher methodMatcher;



    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }


    public void setProxyTargetClass(boolean proxyTargetClass){
        this.proxyTargetClass = proxyTargetClass;
    }
    public boolean isProxyTargetClass(){
        return proxyTargetClass;
    }
}

