package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;

import java.util.List;
import java.util.Queue;

public interface ApplicationService extends StatusChangeable {
    void addApplicationToQueue(Application application) throws ApplicationServiceException;
    void updateApplication(Application application);
    void deleteApplicationFromQueue(Application application);
    Queue<Application> getApplicationQueue();
    Application getApplication(Application application);
    void approveApplication(Application application);
    void denyApplication(Application application, String comments);
    void transferApplicationToEmbassy(Application application, EmbassyService embassyService);
    List<Application> getUserApplications(User user);
}
