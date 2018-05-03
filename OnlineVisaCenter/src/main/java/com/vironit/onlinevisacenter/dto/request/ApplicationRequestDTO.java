package com.vironit.onlinevisacenter.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ApplicationRequestDTO implements Serializable {

    private Integer id;

    private Integer clientInfoId;

    private Integer visaInfoId;

    private Integer checkId;

    private Integer userId;

    private String status;

    private String result;

    private String comments;

    private LocalDateTime creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientInfoId() {
        return clientInfoId;
    }

    public void setClientInfoId(Integer clientInfoId) {
        this.clientInfoId = clientInfoId;
    }

    public Integer getVisaInfoId() {
        return visaInfoId;
    }

    public void setVisaInfoId(Integer visaInfoId) {
        this.visaInfoId = visaInfoId;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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
}
