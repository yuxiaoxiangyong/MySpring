package com.zhangying.myspring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.PropertyValue;
import com.zhangying.myspring.beans.factory.config.BeanDefinition;
import com.zhangying.myspring.beans.factory.config.BeanReference;
import com.zhangying.myspring.beans.factory.support.AbstractBeanDefinitionReader;
import com.zhangying.myspring.beans.factory.support.BeanDefinitionRegistry;
import com.zhangying.myspring.core.io.Resource;
import com.zhangying.myspring.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @className: XmlBeanDefinitionReader
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/4/30 10:38
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }


    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }


    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, BeansException{
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList chileNodes = root.getChildNodes();

        for(int i = 0; i < chileNodes.getLength(); i++){
            if(!(chileNodes.item(i) instanceof Element))continue;
            if(!"bean".equals(chileNodes.item(i).getNodeName()))continue;

            // 解析标签
            Element bean = ((Element) chileNodes.item(i));
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            String initMethodName = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");

            // 优先级 id > name
            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if(StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            beanDefinition.setInitMethodName(initMethodName);
            // 读取属性并进行填充
            for(int j = 0; j < bean.getChildNodes().getLength(); ++j){
                if(!(bean.getChildNodes().item(j) instanceof Element))continue;
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName()))continue;

                // 解析property标签
                Element property = ((Element) bean.getChildNodes().item(j));
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                // 获取属性值：引入对象、值对象 property 配置的可以为基本数据类型||引用数据类型
                Object value = StrUtil.isNotEmpty(attrRef)? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue  = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
