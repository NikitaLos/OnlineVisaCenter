package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;

import java.util.List;
import java.util.Queue;

public interface ApplicationService extends StatusChangeable {
    void addApplicationToQueue(Application application) throws ApplicationServiceException;
    void updateApplication(Application application) throws ApplicationServiceException;
    void deleteApplicationFromQueue(Application application) throws ApplicationServiceException;
    List<Application> getApplications() throws ApplicationServiceException;
    Application getApplication(Application application) throws ApplicationServiceException;
    void approveApplication(Application application) throws ApplicationServiceException;
    void denyApplication(Application application, String comments);
    void transferApplicationToEmbassy(Application application, EmbassyService embassyService);
    List<Application> getUserApplications(User user);
}
