package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Check;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.VisaInfo;
import com.vironit.onlinevisacenter.service.inrefaces.SenderService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSenderServiceImpl implements SenderService {

    private final String username = "losnikita1995@gmail.com";
    private final String password = "Novopolotsk13051995";

    @Override
    public void sendResultToClient(Application application) {
        String email = application.getUser().getEmail();
        String message = application.getResult()+application.getComments();
        sendEmail(email,message);
    }

    @Override
    public void sendCheckToUser(Check check, User user) {

    }

    @Override
    public void sendVisaToUser(VisaInfo visa, User user) {

    }


    private void sendEmail(String email, String messageText){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Your result of visa application");
            message.setText(messageText);
            Transport.send(message);

        } catch (MessagingException e) {
            //todo
        }
    }

}
