package pattern.chain.simple;

import org.junit.Test;

/**
 * 责任链模式
 */
public class Client {
    static class HandlerA extends Handler{
        @Override
        protected void handleProcess() {
            System.out.println("handler A");
        }
    }

    static class HandlerB extends Handler{
        @Override
        protected void handleProcess() {
            System.out.println("handler B");
        }
    }

    static class HandlerC extends Handler{
        @Override
        protected void handleProcess() {
            System.out.println("handler C");
        }
    }

    @Test
    public void testHandler(){
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        Handler handlerC = new HandlerC();

        handlerA.setSucessor(handlerB);
        handlerB.setSucessor(handlerC);

        handlerA.excute();
    }
}
