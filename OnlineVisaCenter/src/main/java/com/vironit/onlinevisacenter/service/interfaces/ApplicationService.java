package com.vironit.onlinevisacenter.service.interfaces;

import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.SenderServiceException;
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
    List<Application> getAllApplications() throws ApplicationServiceException;
    Application getApplication(Integer id) throws ApplicationServiceException;
    Application convertToEntity(ApplicationRequestDTO applicationRequestDTO) throws VisaServiceException, UserServiceException, ApplicationServiceException;
    ApplicationResponseDTO convertToDTO(Application application);

    List<Application> getUserApplications(Integer userId) throws ApplicationServiceException;
    @Transactional
    Application changeApplicationResultAndStatus(Integer id, Result result) throws ApplicationServiceException, SenderServiceException;

    @Transactional
    void addCommentsToApplication(Integer id, String comments) throws ApplicationServiceException;
}
