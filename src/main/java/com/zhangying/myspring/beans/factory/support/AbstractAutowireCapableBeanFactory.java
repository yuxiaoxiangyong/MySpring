package com.zhangying.myspring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.PropertyValue;
import com.zhangying.myspring.beans.PropertyValues;
import com.zhangying.myspring.beans.factory.DisposableBean;
import com.zhangying.myspring.beans.factory.InitializingBean;
import com.zhangying.myspring.beans.factory.config.AutowireCapableBeanFactory;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.config.BeanPostProcessor;
import com.zhangying.myspring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @className: AbstractAutowireCapableBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:09
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

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
            // 实例化Bean      生命周期第一步
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 属性赋值         生命周期第二步
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        // 注册实现了 DisposableBean接口的Bean对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        addSingleton(beanName, bean); // 所以说目前已经实例化的所有bean都是单例的
        return bean;
    }


    /**
     * 抽象得到的创建bean实例方法
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     * @throws BeansException
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException{
        // 解决args为空的异常
        if(null == args)return instantiationStrategy.instantiate(beanDefinition, beanName, null, args);
        Class<?> clazz = beanDefinition.getBean();  // getClass
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


    /**
     * 依赖注入（未解决循环依赖问题）
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue pv : propertyValues.getPropertyValueList()){
                String name = pv.getName();
                Object value = pv.getValue();
                if(value instanceof BeanReference){
                    // 这里使用的一定是无参构造函数实例化Bean
                   value = getBean(((BeanReference) value).getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        }catch (BeansException e){
            e.printStackTrace();
        }

    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }


    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException{
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed");
        }
        ;

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);

        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws Exception{
        // 通过反射调用方法，首先需要知道 调用者  被调用的方法名称  传递的方法参数
        // 两种方式调用init-method：1. 实现接口  2. 配置文件进行配置
        // 1. 实现接口 InitializingBean
        if (wrappedBean instanceof InitializingBean) {
            ((InitializingBean) wrappedBean).afterPropertiesSet();
        }

        // 2. 配置信息 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getBean().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(wrappedBean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }


    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }
}
