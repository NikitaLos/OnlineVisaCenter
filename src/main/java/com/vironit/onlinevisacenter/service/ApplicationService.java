package com.vironit.onlinevisacenter.service;


import com.vironit.onlinevisacenter.dto.application.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.application.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.application.ApplicationReviewDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;

import java.util.List;

public interface ApplicationService {
    void addApplicationToQueue(ApplicationRequestDTO applicationRequestDTO);

    void updateApplication(ApplicationRequestDTO applicationRequestDTO);

    void deleteApplicationFromQueue(Integer id);

    List<ApplicationResponseDTO> getAllApplications();

    ApplicationResponseDTO getApplication(Integer id);

    List<ApplicationResponseDTO> getUserApplications(Integer userId);

    Application reviewApplication(ApplicationReviewDTO applicationReviewDTO);
}
