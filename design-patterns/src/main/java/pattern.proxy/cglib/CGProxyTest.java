package pattern.proxy.cglib;

import org.junit.Test;

public class CGProxyTest {

    /**
     * 打印结果:
     * excute Method getName
     * my name is hali
     * 代理类Person: pattern.proxy.cglib.CGProxyTest$Person@291972f8
     */
    @Test
    public void test(){
        CGProxy cgProxy = new CGProxy();
        Person person = (Person)cgProxy.getInstance(new Person());
        String name = person.getName("hali");
        System.out.println(name);
        System.out.println("代理类Person对象: " + person);
    }

    static class Person{
        String getName(String name){
            return "my name is " + name;
        }
    }
}
