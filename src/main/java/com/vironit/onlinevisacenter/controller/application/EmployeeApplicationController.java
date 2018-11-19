package com.vironit.onlinevisacenter.controller.application;

import com.vironit.onlinevisacenter.dto.application.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.application.ApplicationReviewDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.service.ApplicationService;
import com.vironit.onlinevisacenter.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employee/applications")
public class EmployeeApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private SenderService senderService;

    @GetMapping
    public List<ApplicationResponseDTO> getApplications() {
        return applicationService.getAllApplications();
    }

    @PatchMapping(value = "/review")
    public void reviewApplication(@RequestBody ApplicationReviewDTO applicationReviewDTO) {
        Application application = applicationService.reviewApplication(applicationReviewDTO);
        senderService.sendResultToClient(application);
    }

    @GetMapping(value = "/statuses")
    public Status[] getStatuses() {
        return Status.values();
    }

    @GetMapping(value = "/results")
    public Result[] getResults() {
        return Result.values();
    }
}
