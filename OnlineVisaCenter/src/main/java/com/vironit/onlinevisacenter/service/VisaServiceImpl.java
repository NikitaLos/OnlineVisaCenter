package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.DocumentServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import com.vironit.onlinevisacenter.service.inrefaces.DocumentService;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisaServiceImpl implements VisaService {

    private VisaDAO visaDAO;
    private CountryService countryService;
    private DocumentService documentService;

    @Autowired
    public VisaServiceImpl(VisaDAO visaDAO, CountryService countryService, DocumentService documentService) {
        this.visaDAO = visaDAO;
        this.countryService = countryService;
        this.documentService = documentService;
    }

    @Override
    public void addVisa(Visa visa) throws VisaServiceException, DuplicateException {
        try {
            visaDAO.checkDuplicate(visa);
            visaDAO.save(visa);
        } catch (EntityFindException | EntitySaveException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public void deleteVisa(Visa visa) throws VisaServiceException {
        try {
            visaDAO.delete(visa);
        } catch (EntityDeleteException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public void updateVisa(Visa visa) throws VisaServiceException {
        try {
            visaDAO.update(visa);
        } catch (EntityUpdateException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public Visa getVisa(Integer id) throws VisaServiceException {
        try {
            return visaDAO.find(id);
        } catch (EntityFindException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public void deleteVisaById(Integer id) throws VisaServiceException {
        try {
            visaDAO.deleteById(id);
        } catch (EntityDeleteException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public List<Visa> getAll() throws VisaServiceException {
        try {
            return visaDAO.findAll(Visa.class);
        } catch (EntityFindException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public List<Visa> getVisasByCountry(Country country) throws VisaServiceException {
        try {
            return visaDAO.findVisasByCountry(country);
        } catch (EntityFindException e) {
            throw new VisaServiceException(e);
        }
    }


    @Override
    public Visa convertToEntity(VisaRequestDTO visaDTO) throws CountryServiceException, DocumentServiceException {
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
    }

    @Override
    public VisaResponseDTO convertToDTO(Visa visa) {
        VisaResponseDTO visaResponseDTO = new VisaResponseDTO();
        visaResponseDTO.setId(visa.getId());
        visaResponseDTO.setType(visa.getType());
        visaResponseDTO.setPrice(visa.getPrice());
        visaResponseDTO.setCountry(countryService.convertToDTO(visa.getCountry()));
        List<DocumentTypeDTO> documentTypesDTO = new ArrayList<>();
        for (DocumentType documentType : visa.getRequiredDocumentTypes()){
            documentTypesDTO.add(documentService.convertToDTO(documentType));
        }
        visaResponseDTO.setRequiredDocumentTypes(documentTypesDTO);
        return visaResponseDTO;
    }
}
