package com.vironit.onlinevisacenter.converter.documenttype;

import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeDTOToDocumentTypeConverter implements Converter<DocumentTypeDTO, DocumentType> {

    @Override
    public DocumentType convert(DocumentTypeDTO documentTypeDTO) {
        DocumentType documentType = new DocumentType();
        BeanUtils.copyProperties(documentTypeDTO,documentType);
        return documentType;
    }
}
