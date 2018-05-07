package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.*;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import com.vironit.onlinevisacenter.service.inrefaces.DocumentService;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private ApplicationService applicationService;
    private CountryService countryService;
    private DocumentService documentService;
    private VisaService visaService;

    @Autowired
    public EmployeeController(ApplicationService applicationService, CountryService countryService,
                              DocumentService documentService, VisaService visaService){
        this.applicationService = applicationService;
        this.countryService = countryService;
        this.documentService = documentService;
        this.visaService = visaService;
    }

    @RequestMapping(value = "/view_applications",method = RequestMethod.GET)
    public List<ApplicationResponseDTO> showApplications() throws ApplicationServiceException {
        List<Application> applications = applicationService.getApplications();
        return applications.stream()
                .map(application -> applicationService.convertToDTO(application))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/view_application/{application_id}",method = RequestMethod.GET)
    public ApplicationResponseDTO getApplication(@PathVariable("application_id") Integer applicationId) throws ApplicationServiceException {
        Application application = applicationService.getApplication(applicationId);
        return applicationService.convertToDTO(application);
    }

    @RequestMapping(value = "/view_countries",method = RequestMethod.GET)
    public List<CountryDTO> showCountries() throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        return countries.stream()
                .map(country -> countryService.convertToDTO(country))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/add_country",method = RequestMethod.POST)
    public Message addCountry(@RequestBody CountryDTO countryDTO) throws CountryServiceException, DuplicateException {
        Country country = countryService.convertToEntity(countryDTO);
        countryService.addCountry(country);
        return new Message("success");
    }

    @RequestMapping(value = "/change_country",method = RequestMethod.POST)
    public Message changeCountry(@RequestBody CountryDTO countryDTO) throws CountryServiceException, DuplicateException {
        Country country = countryService.convertToEntity(countryDTO);
        countryService.updateCountry(country);
        return new Message("success");
    }

    @RequestMapping(value = "/delete_country/{country_id}",method = RequestMethod.GET)
    public Message deleteCountry(@PathVariable("country_id") Integer countryId) throws UserServiceException {
        countryService.deleteCountryById(countryId);
        return new Message("success");
    }

    @RequestMapping(value = "/manage_document_types",method = RequestMethod.GET)
    public List<DocumentTypeDTO> showDocumentTypes() throws DocumentServiceException {
        List<DocumentType> documentTypes = documentService.getAll();
        return documentTypes.stream()
                .map(documentType -> documentService.convertToDTO(documentType))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/add_document_type",method = RequestMethod.POST)
    public Message addDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) throws DocumentServiceException, DuplicateException {
        DocumentType documentType = documentService.convertToEntity(documentTypeDTO);
        documentService.addDocument(documentType);
        return new Message("success");
    }

    @RequestMapping(value = "/delete_document_type/{document_type_id}",method = RequestMethod.GET)
    public Message deleteDocumentType(@PathVariable("document_type_id") Integer id) throws DocumentServiceException {
        documentService.deleteDocumentById(id);
        return new Message("success");
    }

    @RequestMapping(value = "/manage_visas",method = RequestMethod.GET)
    public List<VisaResponseDTO> showVisas() throws VisaServiceException {
        List<Visa> visas = visaService.getAll();
        return visas.stream()
                .map(visa -> visaService.convertToDTO(visa))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/add_visa",method = RequestMethod.POST)
    public Message addVisa(@RequestBody VisaRequestDTO visaDTO) throws VisaServiceException, DuplicateException, CountryServiceException, DocumentServiceException {
        Visa visa = visaService.convertToEntity(visaDTO);
        visaService.addVisa(visa);
        return new Message("success");
    }

    @RequestMapping(value = "/change_visa",method = RequestMethod.POST)
    public Message changeVisa(@RequestBody VisaRequestDTO visaDTO) throws VisaServiceException, CountryServiceException, DocumentServiceException {
        Visa visa = visaService.convertToEntity(visaDTO);
        visaService.updateVisa(visa);
        return new Message("success");
    }

    @RequestMapping(value = "/delete_visa/{visa_id}",method = RequestMethod.GET)
    public Message deleteVisa(@PathVariable("visa_id") Integer id) throws VisaServiceException {
        visaService.deleteVisaById(id);
        return new Message("success");
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
    public Message duplicateUser(DuplicateException e) {
        return new Message(e.getMessage());
    }
}

