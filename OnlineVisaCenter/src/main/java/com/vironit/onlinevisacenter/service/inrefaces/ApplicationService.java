package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;

public interface ApplicationService extends StatusChangeable {
    @Transactional
    void addApplicationToQueue(Application application) throws ApplicationServiceException;

    @Transactional
    void updateApplication(Application application) throws ApplicationServiceException;

    @Transactional
    void deleteApplicationFromQueue(Application application) throws ApplicationServiceException;

    @Transactional
    void approveApplication(Application application) throws ApplicationServiceException;

    @Transactional
    void denyApplication(Application application, String comments);

    List<Application> getApplications() throws ApplicationServiceException;

    Application getApplication(Application application) throws ApplicationServiceException;

    void transferApplicationToEmbassy(Application application, EmbassyService embassyService);

    List<Application> getUserApplications(User user);
}
