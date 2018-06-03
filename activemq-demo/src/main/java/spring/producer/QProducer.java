package spring.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

@Component
public class QProducer {

    @Resource
    private JmsTemplate jmsTemplate;
    @Resource(name = "queue")
    private Destination destination;

    public void sendMessage(final String message){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(message);
                return textMessage;
            }
        });
    }
}
