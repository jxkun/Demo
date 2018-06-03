package spring.Main;

import org.apache.log4j.Logger;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import spring.producer.QProducer;
import spring.producer.TProducer;

import java.io.IOException;

public class TopicTest {
    private static final Logger LOGGER = Logger.getLogger(TopicTest.class);

    @Test
    public void testQueueProducer(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/activemq-producer.xml");
        TProducer bean = context.getBean(TProducer.class);
        String message = "topic test message 1";
        bean.sendMessage(message);
        LOGGER.info("Send Message Success");
    }

    @Test
    public void testQueueConsumer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/activemq-consumer-topic.xml");
        LOGGER.info("spring 初始化完成， 等待接受topic消息, loading.....");
        System.in.read();
    }
}
