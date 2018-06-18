package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.SenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@PropertySource("classpath:mail.properties")
public class EmailSenderServiceImpl implements SenderService {

    @Value("${visa_center.username}")
    private String username;
    @Value("${subject}")
    private String subject;
    @Value("${auth}")
    private String auth;
    @Value("${starttls}")
    private String starttls;
    @Value("${host}")
    private String host;
    @Value("${port}")
    private String port;
    @Value("${visa_center.pass}")
    private String password;

    @Override
    public void sendResultToClient(Application application) throws ServiceException {
        String email = application.getUser().getEmail();
        String message = "Your application status: " + application.getStatus() + "\n" +
                         "Your application result: " + application.getResult() + "\n" +
                         "Your application comments: " + application.getComments();
        try {
            sendMessage(email,message);
        } catch (MessagingException e) {
            throw new ServiceException("Error of sending email",e);
        }
    }

    private void sendMessage(String email, String messageText) throws MessagingException {
        Message message = new MimeMessage(createSession());
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        message.setSubject(subject);
        message.setText(messageText);
        Transport.send(message);
    }

    private Properties setUpProperties(){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", starttls);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        return properties;
    }

    private Session createSession(){
        return Session.getInstance(setUpProperties(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });
    }

}







