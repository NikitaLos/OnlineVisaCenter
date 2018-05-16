package com.vironit.onlinevisacenter.service.interfaces;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.exceptions.service.SenderServiceException;

import javax.mail.MessagingException;

public interface SenderService {
    void sendResultToClient(Application application) throws SenderServiceException;
}
