package com.vironit.onlinevisacenter.dto.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.List;

public class VisaRequestDTO {

    private Integer id;

    @NotNull(message = "type can not be null")
    private String type;

    @NotNull(message = "price can not be null")
    @Digits(integer = 4, fraction = 2, message = "price must consist of {integer} integer digits and {fraction} fraction")
    private Double price;

    @NotNull(message = "country can not be null")
    private Integer countryId;

    private List<Integer> requiredDocumentTypesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public List<Integer> getRequiredDocumentTypesId() {
        return requiredDocumentTypesId;
    }

    public void setRequiredDocumentTypesId(List<Integer> requiredDocumentTypesId) {
        this.requiredDocumentTypesId = requiredDocumentTypesId;
    }
}
