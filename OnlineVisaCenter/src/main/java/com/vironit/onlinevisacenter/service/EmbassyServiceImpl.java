//package com.vironit.onlinevisacenter.service;
//
//import com.vironit.onlinevisacenter.entity.Application;
//import com.vironit.onlinevisacenter.entity.VisaInfo;
//import com.vironit.onlinevisacenter.entity.enums.Result;
//import com.vironit.onlinevisacenter.service.inrefaces.EmbassyService;
//
//import java.time.Instant;
//import java.time.LocalDate;
//
//public class EmbassyServiceImpl implements EmbassyService {
//
//    private final String approveComment = "Lucky";
//    private final String denyComment = "Not lucky";
//
//    @Override
//    public void addApplicationToQueue(Application application) {
//        reviewApplication(application);
//    }
//
//    private void reviewApplication(Application application) {
//        if(Instant.now().getEpochSecond()%2==0){
//            approveApplication(application);
//        }else {
//            denyApplication(application);
//        }
//    }
//
//    private void approveApplication(Application application) {
//        application.setResult(Result.APPROVE);
//        addCommentsToApplication(application,approveComment);
//        makeVisa(application);
//    }
//
//    private void denyApplication(Application application) {
//        application.setResult(Result.DENY);
//        addCommentsToApplication(application,denyComment);
//    }
//
//    private void addCommentsToApplication(Application application, String comments) {
//        application.setComments(comments);
//    }
//
//    private void makeVisa(Application application) {
//        VisaInfo visa = application.getVisaInfo();
//        visa.setDateOfReceiving(LocalDate.now());
//        //todo make visa, upload on server
//        visa.setVisaPathOnServer("path");
//    }
//
//}
