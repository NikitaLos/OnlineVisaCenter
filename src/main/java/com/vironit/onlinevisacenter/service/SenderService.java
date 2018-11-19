package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.entity.Application;

public interface SenderService {
    void sendResultToClient(Application application);
}
