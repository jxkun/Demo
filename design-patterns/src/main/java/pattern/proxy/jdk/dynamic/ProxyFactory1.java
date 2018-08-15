package pattern.proxy.jdk.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 */
public class ProxyFactory1 implements InvocationHandler {

    private Object obj;

    public ProxyFactory1(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("excute before " + method.getName());
        Object res = method.invoke(obj, args);
        System.out.println("excute after " + method.getName());
        return res;
    }

    public Object getInstance(){
        return Proxy.newProxyInstance(
                this.obj.getClass().getClassLoader(),
                this.obj.getClass().getInterfaces(),
                this
        );
    }
}
