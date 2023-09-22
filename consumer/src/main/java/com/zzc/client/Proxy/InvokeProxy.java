package com.zzc.client.Proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.zzc.client.annotation.RemoteInvoke;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import com.zzc.client.param.ClientRequest;
import com.zzc.client.param.Response;
import com.zzc.client.core.TCPClient;
import com.zzc.user.bean.User;


@Component
public class InvokeProxy implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(RemoteInvoke.class)){
                field.setAccessible(true);
                final Map<Method,Class>methodClassMap = new HashMap<Method,Class>();
                putMethodClass(methodClassMap,field);
                Enhancer enhancer = new Enhancer();
                enhancer.setInterfaces(new Class[]{field.getType()});
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        //需要调用服务器
                        ClientRequest request = new ClientRequest();
                        User u = new User();
                        u.setId(1);
                        u.setName("zzc");
                        request.setCommand(methodClassMap.get(method).getName()+"."+method.getName());
                        request.setContent(args[0]);
                        Response resp = TCPClient.send(request);
                        return resp;
                    }
                });
                try{
                    field.set(bean,enhancer.create());
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        return bean;
    }
//对属性的所有方法和属性接口类型放入一个map
    private void putMethodClass(Map<Method, Class> methodClassMap, Field field) {
        Method[] methods = field.getType().getDeclaredMethods();
        for(Method method:methods){
            methodClassMap.put(method,field.getType());
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

//@Component
//public class InvokeProxy implements BeanPostProcessor {
//    public static Enhancer enhancer = new Enhancer();
//
//    public Object postProcessAfterInitialization(Object bean, String arg1) throws BeansException {
//        return bean;
//    }
//    //对属性的所有方法和属性类型放入到HashMap中
//    private void putMethodClass(HashMap<Method, Class> methodmap, Field field) {
//        Method[] methods = field.getType().getDeclaredMethods();
//        for(Method method : methods){
//            methodmap.put(method, field.getType());
//        }
//
//    }
//
//    public Object postProcessBeforeInitialization(Object bean, String arg1) throws BeansException {
//		System.out.println(bean.getClass().getName());
//        System.out.println(Arrays.toString(bean.getClass().getDeclaredFields()));
//        Field[] fields = bean.getClass().getDeclaredFields();
//        for(Field field : fields){
//            if(field.isAnnotationPresent(RemoteInvoke.class)){
//                System.out.println("get");
//                field.setAccessible(true);
//
////				final HashMap<Method, Class> methodmap = new HashMap<Method, Class>();
////				putMethodClass(methodmap,field);
////				Enhancer enhancer = new Enhancer();
//                enhancer.setInterfaces(new Class[]{field.getType()});
//                enhancer.setCallback(new MethodInterceptor() {
//
//                    public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//                        ClientRequest clientRequest = new ClientRequest();
//                        clientRequest.setContent(args[0]);
////						String command= methodmap.get(method).getName()+"."+method.getName();
//                        String command = method.getName();//修改
//						System.out.println("InvokeProxy中的Command是:"+command);
//                        clientRequest.setCommand(command);
//
//                        Response response = TCPClient.send(clientRequest);
//                        return response;
//                    }
//                });
//                try {
//                    field.set(bean, enhancer.create());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return bean;
//    }
//
//}