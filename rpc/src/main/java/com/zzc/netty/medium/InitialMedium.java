package com.zzc.netty.medium;

import com.zzc.netty.annotation.Remote;
import com.zzc.netty.annotation.RemoteInvoke;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Map;

@Component
public class InitialMedium implements BeanPostProcessor{
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(Remote.class)){
            //System.out.println(bean.getClass().getName());
            Method[] methods = bean.getClass().getDeclaredMethods();
            for(Method m:methods){
                String key = bean.getClass().getInterfaces()[0].getName()+"."+m.getName();
                Map<String,BeanMethod> beanMap = Media.beanMap;
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(m);
                beanMap.put(key,beanMethod);
            }
        }

        return bean;
    }
}
