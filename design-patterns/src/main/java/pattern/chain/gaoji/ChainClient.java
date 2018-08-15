package pattern.chain.gaoji;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 链式顺序执行
 */
public class ChainClient {

    static class ChainHandlerA extends ChainHandler{
        @Override
        protected void handleProcess() {
            System.out.println("handle by chain a");
        }
    }
    static class ChainHandlerB extends ChainHandler{
        @Override
        protected void handleProcess() {
            System.out.println("handle by chain b");
        }
    }
    static class ChainHandlerC extends ChainHandler{
        @Override
        protected void handleProcess() {
            System.out.println("handle by chain c");
        }
    }

    @Test
    public void test(){
        List<ChainHandler> handlers = Arrays.asList(
                new ChainHandlerA(),
                new ChainHandlerB(),
                new ChainHandlerC());
        Chain chain = new Chain(handlers);
        chain.proceed();
    }
}
