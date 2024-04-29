package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @className: AbstractAutowireCapableBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:09
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();


    /**
     * 支持无参+带参构造实例化Bean
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     * @throws BeansException
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {

        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        addSingleton(beanName, bean);
        return bean;
    }


    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException{
        //
        Class<?> clazz = beanDefinition.getBean();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for(Constructor constructor : constructors){
            // 寻找匹配的Constructor, 参数的顺序和类型都要匹配
            Class<?>[] paramterTypes = constructor.getParameterTypes();
            if(paramterTypes.length != args.length){
                continue;
            }

            boolean isSame = true;
            for(int i = 0; i < args.length; ++i){
                if(!args[i].getClass().equals(paramterTypes[i])){
                    isSame = false;
                    break;
                }
            }

            if(isSame){
                return instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);
            }
        }
        // 表明创建失败
        throw new BeansException();

    }

}
