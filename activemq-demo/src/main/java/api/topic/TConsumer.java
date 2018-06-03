package api.topic;

import api.queue.QProducer;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class TConsumer {
    private static final Logger LOGGER = Logger.getLogger(QProducer.class);

    private static final String USER_NAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    /*private static final  String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;*/
    private static final String BROKER_URL = "tcp://192.168.25.141:61616";

    private static Connection connection;
    private static Session session;
    private static MessageConsumer consumer;

    public static void init(String topicName) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(topicName);
        consumer = session.createConsumer(destination);
    }

    /**
     * 使用receive接受数据
     * @throws JMSException
     */
    public static void getMessageByReceive() throws JMSException {
        while(true){
            LOGGER.info("等待数据...");
            TextMessage textMessage = (TextMessage)consumer.receive(10000);// 毫秒值，10000毫秒接受一次
            if(textMessage != null){
                LOGGER.info("接受到的消息： " + textMessage.getText());
            }
        }
    }

    /**
     * 使用监听接受数据
     * @throws JMSException
     */
    public static void getMessageByListener() throws JMSException {
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    LOGGER.info("接受到的消息： " + textMessage.getText());
                } catch (JMSException e) {
                    LOGGER.error("接受数据发生错误 {}", e);
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args)  {
        try {
            init("test-topic");
            //getMessageByReceive(); // 使用receive接受数据
            getMessageByListener(); // 使用listener 监听， 接受数据

            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
