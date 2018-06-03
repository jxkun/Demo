package spring.Main;


import org.apache.log4j.Logger;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import spring.producer.QProducer;

import java.io.IOException;

public class QueueTest {

    private static final Logger LOGGER = Logger.getLogger(QueueTest.class);

    @Test
    public void testQueueProducer(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/activemq-producer.xml");
        QProducer bean = context.getBean(QProducer.class);
        String message = "queue test message 1";
        bean.sendMessage(message);
        LOGGER.info("Send Message Success");
    }

    @Test
    public void testQueueConsumer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/activemq-consumer-queue.xml");
        LOGGER.info("spring 初始化完成， 等待接受queue消息, loading.....");
        System.in.read();
    }
}
