package com.vironit.onlinevisacenter.controller.application.employee;

import com.vironit.onlinevisacenter.converter.ApplicationConverter;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.ApplicationService;
import com.vironit.onlinevisacenter.service.interfaces.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeApplicationController {

    private ApplicationService applicationService;
    private ApplicationConverter applicationConverter;
    private SenderService senderService;

    @Autowired
    public EmployeeApplicationController(ApplicationService applicationService, ApplicationConverter applicationConverter,
                                         SenderService senderService) {
        this.applicationService = applicationService;
        this.applicationConverter = applicationConverter;
        this.senderService = senderService;
    }

    @GetMapping(value = "/get_applications")
    public List<ApplicationResponseDTO> getApplications() throws ServiceException {
        List<Application> applications = applicationService.getAllApplications();
        return applications.stream()
                .map(application -> applicationConverter.convertToDTO(application))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/add_comments/{application_id}")
    public void addCommentsToApplication(@PathVariable("application_id") Integer id, @RequestParam String comments) throws ServiceException {
        applicationService.addCommentsToApplication(id, comments);
    }

    @GetMapping(value = "/change_result/{application_id}")
    public void changeApplicationResult(@PathVariable("application_id") Integer id, @RequestParam Result result) throws ServiceException {
        Application application = applicationService.changeApplicationResultAndStatus(id, result);
        senderService.sendResultToClient(application);
    }

    @GetMapping(value = "/get_statuses")
    public Status[] getStatuses() {
        return Status.values();
    }

    @GetMapping(value = "/get_results")
    public Result[] getResults() {
        return Result.values();
    }
}
