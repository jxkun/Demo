package pattern.proxy.jdk.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 */
public class ProxyFactory2{
    private Object obj;

    public ProxyFactory2(Object obj) {
        this.obj = obj;
    }

    public Object getInstance(){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("excute before " + method.getName());
                Object res = method.invoke(obj, args);
                System.out.println("excute after " + method.getName());
                return res;
            }
        });
    }
}
