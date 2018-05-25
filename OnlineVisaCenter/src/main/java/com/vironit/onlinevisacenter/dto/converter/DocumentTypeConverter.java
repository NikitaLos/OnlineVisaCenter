package com.vironit.onlinevisacenter.dto.converter;

import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeConverter {

    public DocumentType convertToEntity(DocumentTypeDTO documentTypeDTO){
        DocumentType documentType = new DocumentType();
        documentType.setName(documentTypeDTO.getName());
        documentType.setId(documentTypeDTO.getId());
        return documentType;
    }

    public DocumentTypeDTO convertToDTO(DocumentType documentType){
        DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
        documentTypeDTO.setName(documentType.getName());
        documentTypeDTO.setId(documentType.getId());
        return documentTypeDTO;
    }
}
