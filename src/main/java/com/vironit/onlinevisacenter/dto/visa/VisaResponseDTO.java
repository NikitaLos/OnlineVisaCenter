package com.vironit.onlinevisacenter.dto.visa;

import com.vironit.onlinevisacenter.dto.country.CountryDTO;
import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
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
