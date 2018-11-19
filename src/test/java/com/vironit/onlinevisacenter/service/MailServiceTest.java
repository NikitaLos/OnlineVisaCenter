package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.service.impl.EmailSenderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
@IfProfileValue(name = "spring.profile.active", value = "emailTest")
public class MailServiceTest {
    @Autowired
    public EmailSenderServiceImpl emailSenderService;

    @Mock
    Application application;

    @Mock
    User user;

    @Before
    public void setUp() {
        when(application.getUser()).thenReturn(user);
        when(user.getEmail()).thenReturn("losnikita1995@gmail.com");
        when(application.getStatus()).thenReturn(Status.IN_QUEUE);
        when(application.getResult()).thenReturn(Result.APPROVE);
        when(application.getComments()).thenReturn("all good");
    }

    @Test
    public void sendEmailTest() {
        emailSenderService.sendResultToClient(application);
    }
}
