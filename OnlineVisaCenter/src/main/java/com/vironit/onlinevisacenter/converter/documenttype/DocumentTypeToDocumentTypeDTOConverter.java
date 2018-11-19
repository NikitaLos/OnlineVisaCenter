package com.vironit.onlinevisacenter.converter.documenttype;

import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeToDocumentTypeDTOConverter implements Converter<DocumentType, DocumentTypeDTO> {
    @Override
    public DocumentTypeDTO convert(DocumentType documentType) {
        DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
        BeanUtils.copyProperties(documentType,documentTypeDTO);
        return documentTypeDTO;
    }
}
