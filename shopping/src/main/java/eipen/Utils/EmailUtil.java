package eipen.Utils;

import eipen.pojo.TbUser;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(TbUser user) throws MessagingException {
        //1、要知道邮箱是哪一家的,使用Properties 进行存储
        Properties properties=new Properties();
        properties.setProperty("mail.host","smtp.qq.com");
        properties.setProperty("mail.transport.protocol","smtp");//协议格式
        properties.setProperty("mail.smtp.auth","ture");//当前邮箱要发送邮件需要认证
        //2、用Session 来装这些文件内容
        Session session=Session.getDefaultInstance(properties);
        session.setDebug(true);//显示日志
        Message message=new MimeMessage(session);//创建邮箱
        message.setSubject("我爱Eipen");//邮件标题
        message.setFrom(new InternetAddress("2450034934@qq.com"));//发件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));//收件人
        message.setContent("欢迎注册就骗你网,本网站本着不骗你骗谁的原则,感谢您为诈骗事业做出的伟大贡献,就骗你网温馨提醒" +
                ",请下载反诈中心 app,点击下面链接让我们骗你 http://localhost:8080/active?username="+user.getUsername()+"&code="+user.getCode(),"text/html;charset=utf-8");
        message.setSentDate(new Date());//立即发送
        message.saveChanges();//保存数据

        Transport transport=session.getTransport();
        transport.connect("2450034934@qq.com","yvifjdjzcffsecfb");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

}
