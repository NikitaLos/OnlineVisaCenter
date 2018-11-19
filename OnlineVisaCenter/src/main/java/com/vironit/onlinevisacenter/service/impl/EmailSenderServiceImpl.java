package com.vironit.onlinevisacenter.service.impl;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements SenderService {

    private static final String SUBJECT = "Visa center notification";

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendResultToClient(Application application) throws DuplicateException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String email = application.getUser().getEmail();
        String message = "Your application status: " + application.getStatus() + "\n" +
                "Your application result: " + application.getResult() + "\n" +
                "Your application comments: " + application.getComments();

        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(SUBJECT);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }
}







