package com.vironit.onlinevisacenter.dto.response;

import com.vironit.onlinevisacenter.dto.UserDTO;
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
