package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.ApplicationDAO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.ApplicationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ApplicationServiceTest extends BaseServiceTest{

    @Autowired
    private ApplicationService applicationService;

    @MockBean
    private ApplicationDAO applicationDAO;

    @Test
    public void getApplicationTest() throws DAOException, ServiceException {
        Application application = entityHelper.prepareApplication();
        when(applicationDAO.find(1)).thenReturn(application);
        assertEquals(application,applicationService.getApplication(1));
    }

    @Test
    public void addApplicationToQueueTest() throws DAOException, ServiceException {
        Application application = entityHelper.prepareApplication();
        doAnswer(invocation ->{
                Application app = invocation.getArgument(0);
                assertEquals(app.getResult(),Result.NO_RESULT);
                assertEquals(app.getStatus(),Status.IN_QUEUE);
                assertNotNull(app.getCreationTime());
                return null;
        }).when(applicationDAO).save(any(Application.class));
        applicationService.addApplicationToQueue(application);
    }

    @Test
    public void updateApplicationTest() throws ServiceException, DAOException {
        Application application = entityHelper.prepareApplication();
        applicationService.updateApplication(application);
        verify(applicationDAO,times(1)).update(application);
    }

    @Test
    public void deleteApplicationFromQueueTest() throws ServiceException, DAOException {
        Application application = entityHelper.prepareApplication();
        applicationService.deleteApplicationFromQueue(application);
        verify(applicationDAO,times(1)).delete(application);
    }

    @Test
    public void getAllApplicationTest() throws DAOException, ServiceException {
        Application[] applications = {entityHelper.prepareApplication(),entityHelper.prepareApplication()};
        when(applicationDAO.findAll(Application.class)).thenReturn(Arrays.asList(applications));
        assertEquals(2,applicationService.getAllApplications().size());
    }

    @Test
    public void getUserApplicationsTest() throws DAOException, ServiceException {
        Application[] applications = {entityHelper.prepareApplication(),entityHelper.prepareApplication()};
        when(applicationDAO.findApplicationsByClient(1)).thenReturn(Arrays.asList(applications));
        assertEquals(2,applicationService.getUserApplications(1).size());
    }

    @Test
    public void addCommentsToApplicationTest() throws DAOException, ServiceException {
        Application application = entityHelper.prepareApplication();
        when(applicationDAO.find(1)).thenReturn(application);
        doAnswer(invocation ->{
            Application app = invocation.getArgument(0);
            assertEquals(app.getComments(),"comments");
            return null;
        }).when(applicationDAO).update(any(Application.class));
        applicationService.addCommentsToApplication(1,"comments");
    }

    @Test
    public void changeApplicationResultAndStatusTest() throws DAOException, ServiceException {
        Application application = entityHelper.prepareApplication();
        when(applicationDAO.find(1)).thenReturn(application);
        doAnswer(invocation ->{
            Application app = invocation.getArgument(0);
            assertEquals(app.getResult(),Result.APPROVE);
            assertEquals(app.getStatus(),Status.REVIEWED);
            return null;
        }).when(applicationDAO).update(any(Application.class));
        applicationService.changeApplicationResultAndStatus(1,Result.APPROVE);
    }
}
