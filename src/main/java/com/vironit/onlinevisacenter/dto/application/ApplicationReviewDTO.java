package com.vironit.onlinevisacenter.dto.application;

import com.vironit.onlinevisacenter.entity.enums.Result;
import lombok.Data;

@Data
public class ApplicationReviewDTO {

    private Integer id;

    private Result result;

    private String comments;
}
