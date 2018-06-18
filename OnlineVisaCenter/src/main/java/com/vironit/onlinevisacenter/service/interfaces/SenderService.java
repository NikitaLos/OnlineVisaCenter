package com.vironit.onlinevisacenter.service.interfaces;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.exceptions.ServiceException;

public interface SenderService {
    void sendResultToClient(Application application) throws ServiceException;
}
