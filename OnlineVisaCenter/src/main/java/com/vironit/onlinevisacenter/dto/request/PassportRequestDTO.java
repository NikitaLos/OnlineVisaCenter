package com.vironit.onlinevisacenter.dto.request;


import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PassportRequestDTO {

    private Integer id;

    @NotNull(message = "{passport.number.null}")
    @Size(min = 8, message = "{passport.number.size}")
    private String number;

    @NotNull(message = "{passport.country.null}")
    @Size(min = 2, message = "{passport.country.size}")
    private String countryOfResidence;

    @NotNull(message = "{passport.date_of_receiving.null}")
    @Past(message = "{passport.date_of_receiving}")
    private LocalDate dateOfReceiving;

    @NotNull(message = "{passport.date_of_ending.null}")
    @Future(message = "{passport.date_of_ending}")
    private LocalDate dateOfEnding;
}
