package com.vironit.onlinevisacenter.dto.application;

import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.dto.clientinfo.ClientInfoResponseDTO;
import com.vironit.onlinevisacenter.dto.visainfo.VisaInfoResponseDTO;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationResponseDTO {

    private Integer id;

    private ClientInfoResponseDTO clientInfo;

    private VisaInfoResponseDTO visaInfo;

    private UserDTO user;

    private Status status;

    private Result result;

    private String comments;

    private LocalDateTime creationTime;
}
