package com.vironit.onlinevisacenter.service.impl;

import com.vironit.onlinevisacenter.converter.application.ApplicationRequestDTOToApplicationConverter;
import com.vironit.onlinevisacenter.converter.application.ApplicationToApplicationResponseDTOConverter;
import com.vironit.onlinevisacenter.dto.application.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.application.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.application.ApplicationReviewDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.EntityNotFoundException;
import com.vironit.onlinevisacenter.repository.jpa.ApplicationDAO;
import com.vironit.onlinevisacenter.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDAO applicationDAO;

    @Autowired
    private ApplicationRequestDTOToApplicationConverter toApplicationConverter;

    @Autowired
    private ApplicationToApplicationResponseDTOConverter toApplicationResponseDTOConverter;

    @Override
    public void addApplicationToQueue(ApplicationRequestDTO applicationRequestDTO) {
        Application application = toApplicationConverter.convert(applicationRequestDTO);
        application.setCreationTime(LocalDateTime.now());
        application.setStatus(Status.IN_QUEUE);
        application.setResult(Result.NO_RESULT);
        applicationDAO.save(application);
    }

    @Override
    public void updateApplication(ApplicationRequestDTO applicationRequestDTO) {
        Application application = toApplicationConverter.convert(applicationRequestDTO);
        applicationDAO.save(application);
    }

    @Override
    public void deleteApplicationFromQueue(Integer id) {
        applicationDAO.deleteById(id);
    }

    @Override
    public List<ApplicationResponseDTO> getAllApplications() {
        return applicationDAO.findAll().stream()
                .map((application) -> toApplicationResponseDTOConverter.convert(application))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponseDTO getApplication(Integer id) {
        return toApplicationResponseDTOConverter.convert(applicationDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public List<ApplicationResponseDTO> getUserApplications(Integer userId) {
        return applicationDAO.findByUserId(userId).stream()
                .map((application) -> toApplicationResponseDTOConverter.convert(application))
                .collect(Collectors.toList());
    }

    @Override
    public Application reviewApplication(ApplicationReviewDTO applicationReviewDTO) {
        Integer applicationId = applicationReviewDTO.getId();
        Application application = applicationDAO.findById(applicationId).orElseThrow(() -> new EntityNotFoundException(applicationId));
        Result result = applicationReviewDTO.getResult();
        String comments = applicationReviewDTO.getComments();
        addCommentsToApplication(application, comments);
        changeApplicationResultAndStatus(application, result);
        applicationDAO.save(application);
        return application;
    }

    private void addCommentsToApplication(Application application, String comments) {
        if (comments != null) {
            application.setComments(comments);
        }
    }

    private void changeApplicationResultAndStatus(Application application, Result result) {
        if (result != null) {
            Status status = resolveApplicationStatus(result);
            application.setStatus(status);
            application.setResult(result);
        }
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
