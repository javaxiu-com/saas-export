package com.gyhqq.web.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Map;

@Component
public class EmailProducer {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination destination;

    public void sendMessage(Map<String, String> map) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    mapMessage.setString(entry.getKey(), entry.getValue());
                }
                return mapMessage;
            }
        });
    }
}
