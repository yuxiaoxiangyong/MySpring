package com.zhangying.myspring.aop.aspectj;

import com.zhangying.myspring.aop.Advisor;
import com.zhangying.myspring.aop.Pointcut;
import com.zhangying.myspring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @className: AspectJExpressionPointcutAdvisor
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/2 17:05
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切点
    private AspectJExpressionPointcut pointcut;

    // 具体的拦截方法   通知（新增代码）
    private Advice advice;

    // 切点表达式
    private String expression;


    @Override
    public Advice getAdvice() {
        return advice;
    }


    @Override
    public Pointcut getPointcut() {
        if(null == pointcut){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }


    public void setPointcut(AspectJExpressionPointcut pointcut) {
        this.pointcut = pointcut;
    }


    public void setAdvice(Advice advice) {
        this.advice = advice;
    }


    public void setExpression(String expression) {
        this.expression = expression;
    }
}
