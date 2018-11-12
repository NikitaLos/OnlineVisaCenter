package com.vironit.onlinevisacenter.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CountryDTO {

    private Integer id;

    @NotNull(message = "{country.name.null}")
    @Size(min = 3, message = "{country.name.size}")
    private String name;
}
