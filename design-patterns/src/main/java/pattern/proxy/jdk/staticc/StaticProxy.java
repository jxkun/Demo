package pattern.proxy.jdk.staticc;

/**
 * 静态代理
 */
public class StaticProxy implements Humann{

    private Humann humann;

    public StaticProxy(Humann humann) {
        this.humann = humann;
    }

    @Override
    public String getName() {
        System.out.println("excute before");
        String name = humann.getName();
        System.out.println("excute after");
        return name;
    }
}
