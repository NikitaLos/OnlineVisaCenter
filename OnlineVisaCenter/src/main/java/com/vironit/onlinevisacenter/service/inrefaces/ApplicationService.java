package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    Application getApplication(Integer id) throws ApplicationServiceException;

    void transferApplicationToEmbassy(Application application, EmbassyService embassyService);

    List<Application> getUserApplications(Integer userId) throws ApplicationServiceException;

    Application convertToEntity(ApplicationRequestDTO applicationRequestDTO) throws VisaServiceException, UserServiceException;

    ApplicationResponseDTO convertToDTO(Application application);
}
