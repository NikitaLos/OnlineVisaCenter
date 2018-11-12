package com.vironit.onlinevisacenter.dto.request;


import com.vironit.onlinevisacenter.dto.validation.DateValidateGroup;
import com.vironit.onlinevisacenter.dto.validation.PassportDate;
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

    private User user;

    private Status status;

    private Result result;

    private String comments;

    private LocalDateTime creationTime;
}
