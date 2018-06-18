package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
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
    public void addApplicationToQueue(Application application) throws ServiceException {
        try {
            application.setCreationTime(LocalDateTime.now());
            application.setStatus(Status.IN_QUEUE);
            application.setResult(Result.NO_RESULT);
            applicationDAO.save(application);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateApplication(Application application) throws ServiceException {
        try {
            applicationDAO.update(application);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteApplicationFromQueue(Application application) throws ServiceException {
        try {
            applicationDAO.delete(application);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Application> getAllApplications() throws ServiceException {
        try {
            return  applicationDAO.findAll(Application.class);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Application getApplication(Integer id) throws ServiceException {
        try {
            return applicationDAO.find(id);
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<Application> getUserApplications(Integer userId) throws ServiceException {
        try {
            return applicationDAO.findApplicationsByClient(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCommentsToApplication(Integer id, String comments) throws ServiceException {
        try {
            Application application = applicationDAO.find(id);
            application.setComments(comments);
            applicationDAO.update(application);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Application changeApplicationResultAndStatus(Integer id, Result result) throws ServiceException {
        Status status = resolveApplicationStatus(result);
        try {
            Application application = applicationDAO.find(id);
            application.setResult(result);
            application.setStatus(status);
            applicationDAO.update(application);
            return application;
        } catch (DAOException e) {
            throw new ServiceException(e);
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
