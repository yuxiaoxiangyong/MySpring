package com.zhangying.myspring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.PropertyValue;
import com.zhangying.myspring.beans.PropertyValues;
import com.zhangying.myspring.beans.factory.*;
import com.zhangying.myspring.beans.factory.config.*;

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

            /**
             * 动态代理原理：
             * 实际织入通知的整个流程是借助BeanPostProcessor完成的，动态代理的对象一定是类（Bean），只是动态代理应用的场景是为解决为扩展新的功能
             * 不断创建新的类实现接口（违背OCP原则），从而导致类爆炸的问题。这里使用动态代理的前提是代理对象和目标对象实现公共的接口，因为静态代理的实现就是将目标对象
             * 当做静态代理对象的属性，从而在不改变原有代码的前提下，通过扩展新类来满足开发需求的。
             * 动态代理返回的一定是代理对象，只是代理的过程是在内存中以字节码的形式完成的，符合OCP原则
             * AOP实现原理：
             * 完成aop就是执行切面的过程，切面 = 切点 + 通知（扩展部分的代码），只不过通知部分是通过动态代理完成的。
             * 确定切点是用切点表达式来完成的，通知需要解决拦截器来实现，只有与拦截器所匹配的类型的Bean才可以完成动态代理，匹配过程实际就是利用
             * 切点表达是进行判断的过程。
             */

            // pre. 判断是否返回代理对象  代理对象为满足OCP原则设计的，根本不需要加入到Bean容器中的
            // 这里直接生成代理对象，缺少对代理对象的属性赋值
            // 因此把PostProcessBeforInstantiation的处理内容转移到PostProcessAfterInstantiation中
            bean = resolveBeforeInstantiation(beanName, beanDefinition);
            if(null != bean){
                return bean;
            }
            // 实例化Bean      生命周期第一步
            bean = createBeanInstance(beanDefinition, beanName, args);

            // 在设置bean属性之前，允许BeanPostProcessor修改属性值（@Value @Autowire 注解自动进行DI）
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);

            // 属性赋值         生命周期第二步
            applyPropertyValues(beanName, bean, beanDefinition);

            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);

        } catch (BeansException e) {
            e.printStackTrace();
        }
        // 注册实现了 DisposableBean接口的Bean对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        // 判断Scope singleton or prototype
        if(beanDefinition.isSingleton()){
            addSingleton(beanName, bean); // 所以说目前已经实例化的所有bean都是单例的
        }

        return bean;
    }


    /**
     * 在设置 Bean 属性之前，允许 BeanPostProcessor 修改属性值 （@Value注解 @Autowired注解）
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (null != pvs) {
                    for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }


    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = applyBeanPostProcessorBeforeInstantiation(beanDefinition.getBean(), beanName);
        if (null != bean) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }


    // 注意，此方法为新增方法，与 “applyBeanPostProcessorBeforeInitialization” 是两个方法
    public Object applyBeanPostProcessorBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            if (processor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor)processor).postProcessBeforeInstantiation(beanClass, beanName);
                if (null != result) return result;
            }
        }
        return null;
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

        // invokeAwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        // 1. 执行 BeanPostProcessor Before 处理 (可以感知ApplicationContext)
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed");
        }


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
        // prototype类型的Bean无需执行销毁
        if(!beanDefinition.isSingleton())return;

        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }
}
