package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Check;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.PaymentService;

import java.time.LocalDateTime;
import java.util.Date;

public class CreditCardPaymentServiceImpl implements PaymentService {

    private Double visaCenterFee;

    @Override
    public void payForVisa(Application application,ApplicationService applicationService) {
        Check check = new Check();
        check.setAmount(application.getVisaInfo().getVisa().getPrice()+visaCenterFee);
        check.setApplication(application);
        check.setDateOfPayment(LocalDateTime.now());
        makeCheckDocument(check);
        application.setCheck(check);
//        applicationService.updateApplication(application);
    }

    @Override
    public void makeCheckDocument(Check check){
        //todo
        check.setPathOnServer("check.txt");
    }

    @Override
    public void setVisaCenterFee(Double amount) {
        visaCenterFee = amount;
    }
}
