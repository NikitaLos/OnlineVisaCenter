package com.vironit.onlinevisacenter.dto.request;


import com.vironit.onlinevisacenter.dto.validation.DateValidateGroup;
import com.vironit.onlinevisacenter.dto.validation.VisaDate;
import com.vironit.onlinevisacenter.dto.validation.VisaDays;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@VisaDate(groups = DateValidateGroup.class)
@VisaDays(groups = DateValidateGroup.class)
public class VisaInfoRequestDTO {

    private Integer id;

    @NotNull(message = "{visa_info.visa.null}")
    private Integer visaId;

    @NotNull(message = "{visa_info.date_from.null}")
    @Future(message = "{visa_info.date_from}")
    private LocalDate dateFrom;

    @NotNull(message = "{visa_info.date_to.null}")
    @Future(message = "{visa_info.date_to}")
    private LocalDate dateTo;

    @NotNull(message = "{visa_info.days.null}")
    private Integer numOfDaysResidence;
}
