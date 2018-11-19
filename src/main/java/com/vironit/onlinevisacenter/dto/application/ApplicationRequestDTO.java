package com.vironit.onlinevisacenter.dto.application;


import com.vironit.onlinevisacenter.dto.clientinfo.ClientInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.dto.visainfo.VisaInfoRequestDTO;
import com.vironit.onlinevisacenter.validation.DateValidateGroup;
import com.vironit.onlinevisacenter.validation.PassportDate;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@PassportDate(groups = DateValidateGroup.class)
public class ApplicationRequestDTO implements Serializable {

    private Integer id;

    @Valid
    private ClientInfoRequestDTO clientInfo;

    @Valid
    private VisaInfoRequestDTO visaInfo;

    private UserDTO userDTO;

    private Status status;

    private Result result;

    private String comments;

    private LocalDateTime creationTime;
}
