package com.gyhqq.linstener;

import com.gyhqq.common.utils.EmailUtils;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyMessageLinstener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            String receive = mapMessage.getString("receive");
            String subject = mapMessage.getString("subject");
            String content = mapMessage.getString("content");
            EmailUtils.sendMail(receive, subject, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
