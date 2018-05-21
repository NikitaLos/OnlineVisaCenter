package com.vironit.onlinevisacenter.dto.request;


import com.vironit.onlinevisacenter.dto.validation.DateValidateGroup;
import com.vironit.onlinevisacenter.dto.validation.PassportDate;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDateTime;

@PassportDate(groups = DateValidateGroup.class)
public class ApplicationRequestDTO implements Serializable {

    private Integer id;

    @Valid
    private ClientInfoRequestDTO clientInfo;

    @Valid
    private VisaInfoRequestDTO visaInfo;

    private User user;

    private Status status;

    private Result result;

    private String comments;

    private LocalDateTime creationTime;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public ClientInfoRequestDTO getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfoRequestDTO clientInfo) {
        this.clientInfo = clientInfo;
    }

    public VisaInfoRequestDTO getVisaInfo() {
        return visaInfo;
    }

    public void setVisaInfo(VisaInfoRequestDTO visaInfo) {
        this.visaInfo = visaInfo;
    }
}
