package api.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class QProducer {

    private static final Logger LOGGER = Logger.getLogger(QProducer.class);

    private static final String USER_NAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    /*private static final  String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;*/
    private static final String BROKER_URL = "tcp://192.168.25.141:61616";

    private static Connection connection;
    private static Session session;
    private static MessageProducer producer;

    public static void init(String quueName) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
         // 第一个参数设置为true， 表示使用事务, 第二个参数AUTO_ACKNOWLEDGE 自动确认客户收到消息
        session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(quueName);
        producer = session.createProducer(destination);
    }

    public static void sendMessage(String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(message);
        producer.send(textMessage);
    }

    public static void main(String[] args) {
        System.out.println(ActiveMQConnection.DEFAULT_BROKER_URL);
        try {
            init("test-queue");
            for(int i = 0; i < 10; i++){
                sendMessage("test-queue发送消息， 编号： " + i);
                LOGGER.info("发送消息：" + i);
            }
            session.commit();
            LOGGER.info("提交事务成功");
        } catch (JMSException e) {
            LOGGER.error("发送消息出现问题， {}", e);
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
