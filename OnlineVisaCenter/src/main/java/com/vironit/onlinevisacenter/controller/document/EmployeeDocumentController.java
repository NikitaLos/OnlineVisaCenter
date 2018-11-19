package com.vironit.onlinevisacenter.controller.document;

import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
import com.vironit.onlinevisacenter.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employee/doctypes")
public class EmployeeDocumentController {

    @Autowired
    private DocumentTypeService documentService;

    @GetMapping
    public List<DocumentTypeDTO> getDocumentTypes() {
        return documentService.getAll();
    }

    @PostMapping
    public void addDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
        documentService.addDocument(documentTypeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDocumentType(@PathVariable("id") Integer id) {
        documentService.deleteDocumentById(id);
    }
}
