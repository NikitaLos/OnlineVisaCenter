package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.service.interfaces.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationDAO applicationDAO;

    @Autowired
    public ApplicationServiceImpl(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public void addApplicationToQueue(Application application) throws ApplicationServiceException {
        try {
            application.setCreationTime(LocalDateTime.now());
            application.setStatus(Status.IN_QUEUE);
            application.setResult(Result.NO_RESULT);
            applicationDAO.save(application);
        } catch (EntitySaveException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public void updateApplication(Application application) throws ApplicationServiceException {
        try {
            applicationDAO.update(application);
        } catch (EntityUpdateException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public void deleteApplicationFromQueue(Application application) throws ApplicationServiceException {
        try {
            applicationDAO.delete(application);
        } catch (EntityDeleteException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public List<Application> getAllApplications() throws ApplicationServiceException {
        try {
            return  applicationDAO.findAll(Application.class);
        } catch (EntityFindException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public Application getApplication(Integer id) throws ApplicationServiceException {
        try {
            return applicationDAO.find(id);
        } catch (EntityFindException e) {
            throw  new ApplicationServiceException(e);
        }
    }

    @Override
    public List<Application> getUserApplications(Integer userId) throws ApplicationServiceException {
        try {
            return applicationDAO.findApplicationsByClient(userId);
        } catch (EntityFindException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public void addCommentsToApplication(Integer id, String comments) throws ApplicationServiceException {
        try {
            Application application = applicationDAO.find(id);
            application.setComments(comments);
            applicationDAO.update(application);
        } catch (EntityUpdateException | EntityFindException e) {
            throw new ApplicationServiceException(e);
        }
    }

    @Override
    public Application changeApplicationResultAndStatus(Integer id, Result result) throws ApplicationServiceException {
        Status status = resolveApplicationStatus(result);
        try {
            Application application = applicationDAO.find(id);
            application.setResult(result);
            application.setStatus(status);
            applicationDAO.update(application);
            return application;
        } catch (EntityUpdateException | EntityFindException e) {
            throw new ApplicationServiceException(e);
        }
    }


    private Status resolveApplicationStatus(Result result) {
        if (result==Result.APPROVE||result==Result.DENY){
            return Status.REVIEWED;
        }else if (result==Result.REQUIRED_CHANGES){
            return Status.WAITING_FOR_CHANGES;
        }else{
            return Status.IN_QUEUE;
        }
    }
}
