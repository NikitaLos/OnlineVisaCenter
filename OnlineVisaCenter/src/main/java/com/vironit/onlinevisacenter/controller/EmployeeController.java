package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
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
    public List<Application> showApplications() throws ApplicationServiceException {
        return applicationService.getApplications();
    }

    @RequestMapping(value = "/manage_countries",method = RequestMethod.GET)
    public List<Country> showCountries() throws CountryServiceException {
        return countryService.getAll();
    }

    @RequestMapping(value = "/add_country",method = RequestMethod.POST)
    public Message addCountry(@RequestBody Country country) throws CountryServiceException, DuplicateException {
        countryService.addCountry(country);
        return new Message("success");

    }

    @RequestMapping(value = "/delete_country/{country_id}",method = RequestMethod.GET)
    public Message deleteCountry(@PathVariable("country_id") Integer countryId) throws UserServiceException {
        countryService.deleteCountryById(countryId);
        return new Message("success");
    }

    @RequestMapping(value = "/manage_document_types",method = RequestMethod.GET)
    public List<DocumentType> showDocumentTypes() throws DocumentServiceException {
        return documentService.getAll();
    }

    @RequestMapping(value = "/add_document_type",method = RequestMethod.POST)
    public Message addDocumentType(@RequestBody DocumentType documentType) throws DocumentServiceException, DuplicateException {
        documentService.addDocument(documentType);
        return new Message("success");
    }

    @RequestMapping(value = "/delete_document_type/{document_type_id}",method = RequestMethod.GET)
    public Message deleteDocumentType(@PathVariable("document_type_id") Integer id) throws DocumentServiceException {
        documentService.deleteDocumentById(id);
        return new Message("success");
    }

    @RequestMapping(value = "/manage_visas",method = RequestMethod.GET)
    public List<Visa> showVisas() throws VisaServiceException {
        return visaService.getAll();
    }

    @RequestMapping(value = "/add_visa",method = RequestMethod.POST)
    public Message addVisa(@RequestBody VisaRequestDTO visaDTO) throws VisaServiceException, DuplicateException, CountryServiceException, DocumentServiceException {
        Visa visa = visaService.mapDTOToEntity(visaDTO);
        visaService.addVisa(visa);
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

