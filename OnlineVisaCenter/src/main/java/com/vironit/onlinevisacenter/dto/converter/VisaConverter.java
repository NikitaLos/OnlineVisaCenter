package com.vironit.onlinevisacenter.dto.converter;

import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.ConverterException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.CountryService;
import com.vironit.onlinevisacenter.service.interfaces.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisaConverter {

    private CountryService countryService;
    private DocumentTypeService documentService;
    private CountryConverter countryConverter;
    private DocumentTypeConverter documentTypeConverter;

    @Autowired
    public VisaConverter(CountryService countryService, DocumentTypeService documentService, CountryConverter countryConverter,
                         DocumentTypeConverter documentTypeConverter) {
        this.countryService = countryService;
        this.documentService = documentService;
        this.countryConverter = countryConverter;
        this.documentTypeConverter = documentTypeConverter;
    }
    public Visa convertToEntity(VisaRequestDTO visaDTO) throws ConverterException {
        try {
            Visa visa = new Visa();
            visa.setId(visaDTO.getId());
            visa.setType(visaDTO.getType());
            visa.setPrice(visaDTO.getPrice());
            visa.setCountry(countryService.getCountry(visaDTO.getCountryId()));
            if(visaDTO.getRequiredDocumentTypesId()!=null) {
                for (int id : visaDTO.getRequiredDocumentTypesId()) {
                    visa.addDocumentType(documentService.getDocument(id));
                }
            }
            return visa;
        } catch (ServiceException e) {
            throw new ConverterException("Error of converting VisaDTO to entity",e);
        }

    }

    public VisaResponseDTO convertToDTO(Visa visa) {
        VisaResponseDTO visaResponseDTO = new VisaResponseDTO();
        visaResponseDTO.setId(visa.getId());
        visaResponseDTO.setType(visa.getType());
        visaResponseDTO.setPrice(visa.getPrice());
        visaResponseDTO.setCountry(countryConverter.convertToDTO(visa.getCountry()));
        List<DocumentTypeDTO> documentTypesDTO = new ArrayList<>();
        for (DocumentType documentType : visa.getRequiredDocumentTypes()){
            documentTypesDTO.add(documentTypeConverter.convertToDTO(documentType));
        }
        visaResponseDTO.setRequiredDocumentTypes(documentTypesDTO);
        return visaResponseDTO;
    }
}
