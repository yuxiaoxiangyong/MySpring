package com.zhangying.myspring.context;

import com.zhangying.myspring.beans.BeansException;
import com.zhangying.myspring.beans.factory.Aware;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 * @className: ApplicationContextAware
 * @author: Ying Zhang
 * @version: 1.0
 * @date: 2024/5/1 19:51
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext)throws BeansException;

}
