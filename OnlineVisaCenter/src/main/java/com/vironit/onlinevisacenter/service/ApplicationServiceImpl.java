package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.repository.jpa.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationDAO applicationDAO;

    @Autowired
    public ApplicationServiceImpl(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public void addApplicationToQueue(Application application) {
        application.setCreationTime(LocalDateTime.now());
        application.setStatus(Status.IN_QUEUE);
        application.setResult(Result.NO_RESULT);
        applicationDAO.save(application);
    }

    @Override
    public void updateApplication(Application application) {
        applicationDAO.save(application);
    }

    @Override
    public void deleteApplicationFromQueue(Application application) {
        applicationDAO.delete(application);
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationDAO.findAll();
    }

    @Override
    public Application getApplication(Integer id) throws ServiceException {
        return applicationDAO.findById(id).orElseThrow(ServiceException::new);
    }

    @Override
    public List<Application> getUserApplications(Integer userId) {
        return applicationDAO.findByUserId(userId);
    }

    @Override
    public void addCommentsToApplication(Integer id, String comments) {
        Application application = applicationDAO.findById(id).orElseThrow(SecurityException::new);
        application.setComments(comments);
        applicationDAO.save(application);
    }

    @Override
    public Application changeApplicationResultAndStatus(Integer id, Result result) {
        Status status = resolveApplicationStatus(result);
        Application application = applicationDAO.findById(id).orElseThrow(SecurityException::new);
        application.setResult(result);
        application.setStatus(status);
        applicationDAO.save(application);
        return application;

    }


    private Status resolveApplicationStatus(Result result) {
        if (result == Result.APPROVE || result == Result.DENY) {
            return Status.REVIEWED;
        } else if (result == Result.REQUIRED_CHANGES) {
            return Status.WAITING_FOR_CHANGES;
        } else {
            return Status.IN_QUEUE;
        }
    }
}
