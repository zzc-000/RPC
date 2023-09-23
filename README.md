# RPC
## 本项目为基于Netty，Spring和zookeeper的RPC框架，实现了以下内容：
实现长连接：reentrantLock
实现异步调用：Wait-notify
实现注解调用几乎零配置
实现JSON序列化
实现心跳检测：IdleStateHandler()
实现客户端自启动
实现请求超时判断：后台守护线程
实现客户端请求动态代理：遍历Field找到目标注解，使用Enhancer拦截实现动态代理
实现服务端注册
实现客户端监听发现服务端：实现CuratorWatcher的process方法

# 架构图






# 如何使用基于注解的动态代理的客户端与服务端
## 服务端
### 服务端通过@Remote注解找到对应的接口得到信息后放入BeanMap，以便后续与客户端发来的请求对比来调用。
```
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
```
### 定义自己的服务，加上@Service
```
@Service
public class UserService {
    public static void save(User user){
        //sql
    }

    public static void saveList(List<User> users) {
    }
```
### 服务接口（同时定义与服务端和客户端）
```
public interface TestRemote {
        public Object testUser(User user);
        public Object testUsers(List<User> users);
}

```
### 该接口的实现类（真正调用服务方法对应的对象）
```
@Remote
public class TestRemoteImpl implements TestRemote{
    @Resource
    private UserService userService;
    public Object testUser(User user){
        UserService.save(user);
        return ResponseUtil.createSuccessResults(user);
    }
    public Object testUsers(List<User> users){
        UserService.saveList(users);
        return ResponseUtil.createSuccessResults(users);
    }
}

```
## 客户端
### 在public static TestRemote TestRemote1 这个接口属性上加上注解@RemoteInvoke，运行时在postProcessBeforeInitialization方法中得到该注解对应Field的信息，然后Enhancer拦截，生成请求，再调用客户端发送请求。
```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RemoteInvokingTest.class)
@ComponentScan("\\")
public class RemoteInvokingTest {
    public static List<User> list = new ArrayList<User>();
    @RemoteInvoke
    public static TestRemote TestRemote1;
    public static User user;

    static{
        user = new User();
        user.setId(1);
        user.setName("zzc");
    }
    @Test
    public void testSaveUser(){
         TestRemote1.testUser(user);
    }
}
```
