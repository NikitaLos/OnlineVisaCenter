package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Check;

public interface PaymentService {

    void payForVisa(Application application, ApplicationService applicationService);

    void makeCheckDocument(Check check);

    void setVisaCenterFee(Double amount);
}
