package com.vironit.onlinevisacenter.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;

import java.time.LocalDateTime;

public class ApplicationResponseDTO {

    private Integer id;

    private ClientInfoResponseDTO clientInfo;

    private VisaInfoResponseDTO visaInfo;

    private UserDTO user;

    private Status status;

    private Result result;

    private String comments;

//    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
    private LocalDateTime creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientInfoResponseDTO getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfoResponseDTO clientInfo) {
        this.clientInfo = clientInfo;
    }

    public VisaInfoResponseDTO getVisaInfo() {
        return visaInfo;
    }

    public void setVisaInfo(VisaInfoResponseDTO visaInfo) {
        this.visaInfo = visaInfo;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

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
}
