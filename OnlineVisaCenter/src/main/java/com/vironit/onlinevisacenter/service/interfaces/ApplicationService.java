package com.vironit.onlinevisacenter.service.interfaces;


import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.SenderServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicationService {
    @Transactional
    void addApplicationToQueue(Application application) throws ApplicationServiceException;
    @Transactional
    void updateApplication(Application application) throws ApplicationServiceException;
    @Transactional
    void deleteApplicationFromQueue(Application application) throws ApplicationServiceException;
    List<Application> getAllApplications() throws ApplicationServiceException;
    Application getApplication(Integer id) throws ApplicationServiceException;
    List<Application> getUserApplications(Integer userId) throws ApplicationServiceException;
    @Transactional
    Application changeApplicationResultAndStatus(Integer id, Result result) throws ApplicationServiceException;

    @Transactional
    void addCommentsToApplication(Integer id, String comments) throws ApplicationServiceException;
}
