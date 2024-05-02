package com.zhangying.myspring.beans.factory.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.BeanFactory;
import com.zhangying.myspring.beans.factory.FactoryBean;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.config.BeanPostProcessor;
import com.zhangying.myspring.beans.factory.config.ConfigurableBeanFactory;
import com.zhangying.myspring.util.ClassUtils;
import com.zhangying.myspring.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;
/**
 * @AbstractBeanFactory: 模板抽象类
 * @className: AbstractBeanFactory
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/29 16:09
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /** ClassLoader to resolve bean class names with, if necessary */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    /**
     * String resolvers to apply e.g. to annotation attribute values
     */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();


    @Override
    public Object getBean(String beanName) throws BeansException{
        return doGet(beanName, null);
    }


    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGet(beanName, args);
    }


    @Override
    /**
     * getBean默认返回的是Object类型，通常需要进行向下转型，
     * 该方法辅助完成向下转型过程
     */
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return ((T) getBean(beanName));
    }


    protected <T> T doGet(String beanName, Object[] args) throws BeansException{
        Object bean = getSingleton(beanName);
        if(bean != null){
            return ((T) getObjectForBeanInstance(bean, beanName));
        }

        BeanDefinition beanDefinition = getBeanDefination(beanName);
        Object currentBean = createBean(beanName, beanDefinition, args);
        return (T)getObjectForBeanInstance(currentBean, beanName);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            // 当前bean为新创建的
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }


    protected abstract BeanDefinition getBeanDefination(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }


    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }


    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }


}
