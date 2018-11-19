package com.vironit.onlinevisacenter.dto.visa;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class VisaRequestDTO {

    private Integer id;

    @NotNull(message = "{visa.type.null}")
    private String type;

    @NotNull(message = "{visa.price.null}")
    @Digits(integer = 4, fraction = 2, message = "{visa.price}")
    private Double price;

    @NotNull(message = "{visa.country.null}")
    private Integer countryId;

    private List<Integer> requiredDocumentTypesId;
}
