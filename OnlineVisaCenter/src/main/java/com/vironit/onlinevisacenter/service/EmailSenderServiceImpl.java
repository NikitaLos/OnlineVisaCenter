package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.service.SenderServiceException;
import com.vironit.onlinevisacenter.service.interfaces.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@PropertySource("classpath:mail.properties")
public class EmailSenderServiceImpl implements SenderService {

    private Environment environment;

    @Autowired
    public EmailSenderServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void sendResultToClient(Application application) throws SenderServiceException {
        String email = application.getUser().getEmail();
        String message = "Your application status: " + application.getStatus() + "\n" +
                         "Your application result: " + application.getResult() + "\n" +
                         "Your application comments: " + application.getComments();
        sendMessage(email,message);
    }

    private void sendMessage(String email, String messageText) throws SenderServiceException {
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(environment.getProperty("visa_center.username")));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(environment.getProperty("subject"));
            message.setText(messageText);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new SenderServiceException("Error of sending email",e);
        }
    }

    private Properties setUpProperties(){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", environment.getProperty("auth"));
        properties.put("mail.smtp.starttls.enable", environment.getProperty("starttls"));
        properties.put("mail.smtp.host", environment.getProperty("host"));
        properties.put("mail.smtp.port", environment.getProperty("port"));
        return properties;
    }

    private Session createSession(){
        return Session.getInstance(setUpProperties(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(environment.getProperty("visa_center.username"),
                                environment.getProperty("visa_center.pass"));
                    }
                });
    }

}







