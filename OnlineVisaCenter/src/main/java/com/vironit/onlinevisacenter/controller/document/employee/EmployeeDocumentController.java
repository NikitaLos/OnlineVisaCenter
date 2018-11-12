package com.vironit.onlinevisacenter.controller.document.employee;

import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.converter.DocumentTypeConverter;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeDocumentController {

    private DocumentTypeService documentService;
    private DocumentTypeConverter documentTypeConverter;

    @Autowired
    public EmployeeDocumentController(DocumentTypeService documentService, DocumentTypeConverter documentTypeConverter) {
        this.documentService = documentService;
        this.documentTypeConverter = documentTypeConverter;
    }

    @GetMapping(value = "/get_document_types")
    public List<DocumentTypeDTO> getDocumentTypes() throws ServiceException {
        List<DocumentType> documentTypes = documentService.getAll();
        return documentTypes.stream()
                .map(documentType -> documentTypeConverter.convertToDTO(documentType))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add_document_type")
    public void addDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) throws ServiceException {
        DocumentType documentType = documentTypeConverter.convertToEntity(documentTypeDTO);
        documentService.addDocument(documentType);
    }

    @DeleteMapping(value = "/delete_document_type/{document_type_id}")
    public void deleteDocumentType(@PathVariable("document_type_id") Integer id) throws ServiceException {
        documentService.deleteDocumentById(id);
    }
}
