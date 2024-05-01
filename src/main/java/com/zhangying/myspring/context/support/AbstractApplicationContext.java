package com.zhangying.myspring.context.support;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.ConfigurableListableBeanFactory;
import com.zhangying.myspring.beans.factory.config.BeanFactoryPostProcessor;
import com.zhangying.myspring.beans.factory.config.BeanPostProcessor;
import com.zhangying.myspring.context.ConfigurableApplicationContext;
import com.zhangying.myspring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 *  * Abstract implementation of the {@link com.zhangying.myspring.context.ApplicationContext}
 *  * interface. Doesn't mandate the type of storage used for configuration; simply
 *  * implements common context functionality. Uses the Template Method design pattern,
 *  * requiring concrete subclasses to implement abstract methods.
 *  * <p>
 *  * 抽象应用上下文
 *  * <p>
 * @className: AbstractApplicationContext
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 19:30
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        // 实际已经将 BeanFactoryPostProcessor BeanPostProcessor 注册了
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 2-3. 添加ApplicationContextAwareProcessor，
        // 让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作  （单独的容器List）
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons(); // @link DefaultListalbleBeanFactory

    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor); // @link AbstractBeanFactory
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
