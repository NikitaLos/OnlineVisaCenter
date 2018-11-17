package com.vironit.onlinevisacenter.service.interfaces;


import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicationService {
    void addApplicationToQueue(Application application) throws ServiceException;
    void updateApplication(Application application) throws ServiceException;
    void deleteApplicationFromQueue(Application application) throws ServiceException;
    List<Application> getAllApplications() throws ServiceException;
    Application getApplication(Integer id) throws ServiceException;
    List<Application> getUserApplications(Integer userId) throws ServiceException;
    Application changeApplicationResultAndStatus(Integer id, Result result) throws ServiceException;
    void addCommentsToApplication(Integer id, String comments) throws ServiceException;
}
