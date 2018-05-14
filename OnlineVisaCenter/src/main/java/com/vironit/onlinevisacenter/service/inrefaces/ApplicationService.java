package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicationService {
    @Transactional
    void addApplicationToQueue(Application application) throws ApplicationServiceException;

    @Transactional
    void updateApplication(Application application) throws ApplicationServiceException;

    @Transactional
    void deleteApplicationFromQueue(Application application) throws ApplicationServiceException;

    List<Application> getApplications() throws ApplicationServiceException;

    Application getApplication(Integer id) throws ApplicationServiceException;

    List<Application> getUserApplications(Integer userId) throws ApplicationServiceException;

    Application convertToEntity(ApplicationRequestDTO applicationRequestDTO) throws VisaServiceException, UserServiceException;

    ApplicationResponseDTO convertToDTO(Application application);

    @Transactional
    void addCommentsToApplication(Integer id, String comments) throws ApplicationServiceException;

    @Transactional
    void changeApplicationStatus(Integer id, Status status) throws ApplicationServiceException;
}
