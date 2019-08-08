package com.gyhqq.common.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    /**
     * 实现邮件返送
     *
     * @param receive 邮件的接收邮箱地址
     * @param subject 邮件主题
     * @param context 邮件正文
     * @throws Exception
     */
    public static void sendMail(String receive, String subject, String context) throws Exception {
        /**
         * 邮件设置
         * 1.指定smtp服务器
         * 2.开启身份验证
         */
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "smtp.163.com");   //设置主机地址  smtp.qq.com  smtp.sina.com
        properties.setProperty("mail.smtp.auth", "true");   //认证
        //2.产生一个用于邮件发送的Session对象
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        //3.产生一个邮件的消息对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //4.设置消息的发送者
        mimeMessage.setFrom(new InternetAddress("andy329923@163.com", "通知消息", "UTF-8"));
        //5.设置消息的接收者 //TO 直接发送  CC抄送    BCC密送
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "个人网站", "UTF-8"));
        //6.设置主题
        mimeMessage.setSubject(subject, "UTF-8");
        //7.设置正文
        mimeMessage.setContent(context, "text/html;charset=UTF-8");
        mimeMessage.setSentDate(new Date());
        mimeMessage.saveChanges();
        //8.准备发送，得到火箭
        Transport transport = session.getTransport();
        //9.设置火箭的发射目标
        transport.connect("andy329923@163.com", "andy329923");
        //10.发送
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        //11.关闭
        transport.close();

//        Properties properties = new Properties();
//        properties.setProperty("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.smtp.host", "smtp.163.com");
//        properties.setProperty("mail.smtp.auth", "true");
//        Session session = Session.getDefaultInstance(properties);
//        session.setDebug(true);
//        MimeMessage mimeMessage = new MimeMessage(session);
//        mimeMessage.setFrom(new InternetAddress("andy329923@163.com", "通知消息", "UTF-8"));
//        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "个人网站", "UTF-8"));
//        mimeMessage.setSubject(subject, "UTF-8");
//        mimeMessage.setContent(context, "text/html;charset=UTF-8");
//        mimeMessage.setSentDate(new Date());
//        mimeMessage.saveChanges();
//        Transport transport = session.getTransport();
//        transport.connect("andy329923@163.com", "andy329923");
//        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//        transport.close();
    }

}
