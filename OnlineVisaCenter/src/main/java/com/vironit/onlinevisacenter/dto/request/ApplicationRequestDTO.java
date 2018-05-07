package com.vironit.onlinevisacenter.dto.request;

import com.vironit.onlinevisacenter.dto.ClientInfoDTO;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ApplicationRequestDTO implements Serializable {

    private Integer id;

    private ClientInfoDTO clientInfo;

    private VisaInfoRequestDTO visaInfo;

    private Integer userId;

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

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ClientInfoDTO getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfoDTO clientInfo) {
        this.clientInfo = clientInfo;
    }

    public VisaInfoRequestDTO getVisaInfo() {
        return visaInfo;
    }

    public void setVisaInfo(VisaInfoRequestDTO visaInfo) {
        this.visaInfo = visaInfo;
    }
}
