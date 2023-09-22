package com.zzc.netty.medium;

import java.lang.reflect.Method;

public class BeanMethod {
    private Object bean;
    private Method method;

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
