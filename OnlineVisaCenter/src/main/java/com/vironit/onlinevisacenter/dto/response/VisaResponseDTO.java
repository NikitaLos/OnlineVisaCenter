package com.vironit.onlinevisacenter.dto.response;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;

import java.util.List;

public class VisaResponseDTO {

    private Integer id;

    private String type;

    private Double price;

    private CountryDTO country;

    private List<DocumentTypeDTO> requiredDocumentTypes;

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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public List<DocumentTypeDTO> getRequiredDocumentTypes() {
        return requiredDocumentTypes;
    }

    public void setRequiredDocumentTypes(List<DocumentTypeDTO> requiredDocumentTypes) {
        this.requiredDocumentTypes = requiredDocumentTypes;
    }
}
