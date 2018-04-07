package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.*;

public interface SenderService {
    void sendResultToClient(Application application);
    void sendCheckToUser(Check check, User user);
    void sendVisaToUser(VisaInfo visa, User user);
}
