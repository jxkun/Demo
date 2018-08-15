package pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理
 */
public class CGProxy implements MethodInterceptor {

    private Object obj;

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if("getName".equals(method.getName())){
            System.out.println("excute Method getName");
        }
        return method.invoke(obj, args);
    }

    public Object getInstance(Object obj){
        this.obj = obj;
        Enhancer enhancer = new Enhancer(); // 设置工具类
        enhancer.setSuperclass(obj.getClass()); // 设置父类
        enhancer.setCallback(this); // 设置回调函数
        return enhancer.create(); // 创建代理类
    }
}
