package com.zhangying.myspring.aop;

/**
 * 被代理的目标对象
 * @className: TargetSource
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 15:10
 */
public class TargetSource {

    private final Object target;


    public TargetSource(Object target) {
        this.target = target;
    }


    /**
     * 实际返回的是目标对象和代理对象共同的接口
     * Return the type of targets returned by this {@link TargetSource}.
     * <p>Can return <code>null</code>, although certain usages of a
     * <code>TargetSource</code> might just work with a predetermined
     * target class.
     * @return the type of targets returned by this {@link TargetSource}
     */
    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }


    /**
     * Return a target instance. Invoked immediately before the
     * AOP framework calls the "target" of an AOP method invocation.
     * @return the target object, which contains the joinpoint
     * @throws Exception if the target object can't be resolved
     */
    public Object getTarget(){
        return this.target;
    }

}
