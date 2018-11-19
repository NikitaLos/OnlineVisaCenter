package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;

import java.util.List;

public interface DocumentTypeService {
    void addDocument(DocumentTypeDTO documentTypeDTO);

    void deleteDocumentById(Integer id);

    DocumentTypeDTO getDocument(Integer id);

    List<DocumentTypeDTO> getAll();

}
