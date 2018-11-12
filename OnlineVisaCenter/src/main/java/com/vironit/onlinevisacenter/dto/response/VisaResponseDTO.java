package com.vironit.onlinevisacenter.dto.response;

import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import lombok.Data;

import java.util.List;

@Data
public class VisaResponseDTO {

    private Integer id;

    private String type;

    private Double price;

    private CountryDTO country;

    private List<DocumentTypeDTO> requiredDocumentTypes;
}
