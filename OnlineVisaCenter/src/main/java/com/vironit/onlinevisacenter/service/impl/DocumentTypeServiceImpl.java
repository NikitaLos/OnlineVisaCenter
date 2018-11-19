package com.vironit.onlinevisacenter.service.impl;

import com.vironit.onlinevisacenter.converter.documenttype.DocumentTypeDTOToDocumentTypeConverter;
import com.vironit.onlinevisacenter.converter.documenttype.DocumentTypeToDocumentTypeDTOConverter;
import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.EntityNotFoundException;
import com.vironit.onlinevisacenter.repository.jpa.DocumentTypeDAO;
import com.vironit.onlinevisacenter.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeDAO documentTypeDAO;

    @Autowired
    private DocumentTypeToDocumentTypeDTOConverter toDocumentTypeDTOConverter;

    @Autowired
    private DocumentTypeDTOToDocumentTypeConverter toDocumentTypeConverter;

    @Override
    public void addDocument(DocumentTypeDTO documentTypeDTO) {
        DocumentType documentType = toDocumentTypeConverter.convert(documentTypeDTO);
        checkDuplicate(documentType);
        documentTypeDAO.save(documentType);
    }

    @Override
    public DocumentTypeDTO getDocument(Integer id) {
        return toDocumentTypeDTOConverter.convert(documentTypeDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public List<DocumentTypeDTO> getAll() {
        return documentTypeDAO.findAll().stream()
                .map((documentType -> toDocumentTypeDTOConverter.convert(documentType)))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDocumentById(Integer id) {
        documentTypeDAO.deleteById(id);
    }

    private void checkDuplicate(DocumentType documentType) {
        if (!documentTypeDAO.findByName(documentType.getName()).isEmpty()) {
            throw new DuplicateException("Such a document type already exists");
        }
    }
}
