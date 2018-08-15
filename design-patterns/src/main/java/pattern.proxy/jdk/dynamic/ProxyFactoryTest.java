package pattern.proxy.jdk.dynamic;

import org.junit.Test;

public class ProxyFactoryTest {
    /**
     * 执行结果:
     * excute before createName
     * excute after createName
     * hali: your name is;
     * excute before toString
     * excute after toString
     * 代理类Person对象: pattern.proxy.jdk.dynamic.ProxyFactoryTest$Person@c4179ba
     */
    @Test
    public void testProxyFactory1(){
        ProxyFactory1 proxyFactory1 = new ProxyFactory1(new Person());
        Human human1 = (Human)proxyFactory1.getInstance();
        System.out.println(human1.createName("hali"));
        System.out.println("代理类Person对象: " + human1);
    }

    @Test
    public void testProxyFactory2(){
        ProxyFactory2 proxyFactory2 = new ProxyFactory2(new Person());
        Human human2 = (Human)proxyFactory2.getInstance();
        System.out.println(human2.createName("pote"));
        System.out.println("代理类Person对象: " + human2);
    }

    static interface Human{
        String createName(String name);
    }
    static class Person implements Human{
        @Override
        public String createName(String name){
            return name + ": your name is;";
        }
    }
}
