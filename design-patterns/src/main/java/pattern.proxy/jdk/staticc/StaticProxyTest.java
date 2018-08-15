package pattern.proxy.jdk.staticc;

import org.junit.Test;

public class StaticProxyTest {

    /**
     * 执行结果
     * excute before
     * excute after
     * harry potter
     */
    @Test
    public void testStaticProxy(){
        Humann humann = (Humann)new StaticProxy(new Personn());
        System.out.println(humann.getName());
    }


    static class Personn implements Humann{
        @Override
        public String getName() {
            return "harry potter";
        }
    }
}
