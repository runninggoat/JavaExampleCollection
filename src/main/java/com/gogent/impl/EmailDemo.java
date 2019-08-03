package com.gogent.impl;

import com.gogent.annotation.Example;
import com.gogent.interfaces.AbstractExampleExecutable;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Example(command = "email", ref = EmailDemo.class, description = "发送邮件示例")
public class EmailDemo extends AbstractExampleExecutable {

    Scanner scanner = new Scanner(System.in);

    Pattern p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    Matcher m;

    public void process() {
        // 收件人电子邮箱
//        String to = "1501832474@qq.com";
        System.out.println("请输入正确的收件人邮箱地址：");
        String to = getNextValidEmailAddress();
        // 发件人电子邮箱
        System.out.println("请输入正确的发件人邮箱地址(@163.com，并且需要已经开通授权码)：");
        String from = getNextValidEmailAddress();
        // 指定发送邮件的主机
        String host = "smtp.163.com";
        // 必须是SMTP服务的授权码，不一定是密码
//        String password = "xxx";
        System.out.println("请输入发件人邮箱的<授权码>（不一定是密码）：");
        String password = scanner.next();
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器，主机、需要验证、smtp服务器（主机类型）
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        // 获取默认session对象
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        String subjectTitle = "";
        String content = "";
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 163邮箱必须要抄送一份邮件给自己的邮箱，防止被554反垃圾邮件阻挡
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(from));
            // Set Subject: 头部头字段
            System.out.println("请输入发送邮件的主题：");
            subjectTitle = scanner.next();
            message.setSubject(subjectTitle);
            // 设置消息体
            System.out.println("请输入发送邮件的文本内容：");
            content = scanner.next();
            message.setText(content);
            // 连接邮件服务主机发送消息
            Transport transport = session.getTransport();
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.printf("邮件发送成功，邮件关键信息：\n收件人：%s\n邮件主题：%s\n邮件内容：%s\n", to, subjectTitle, content);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("邮件发送失败，邮件关键信息：\n收件人：%s\n邮件主题：%s\n邮件内容：%s\n", to, subjectTitle, content);
        }
    }

    private String getNextValidEmailAddress() {
        String result = "";
        Boolean block = true;
        while (block) {
            result = scanner.next();
            m = p.matcher(result);
            if (m.matches()) {
                block = false;
            } else {
                System.out.println("邮件地址格式异常，请重新输入");
            }
        }
        return result;
    }
}
