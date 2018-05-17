package com.vironit.onlinevisacenter.controller;

import org.apache.log4j.Logger;
import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.response.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.*;
import com.vironit.onlinevisacenter.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private ApplicationService applicationService;
    private CountryService countryService;
    private DocumentService documentService;
    private VisaService visaService;
    private SenderService senderService;

    @Autowired
    public EmployeeController(ApplicationService applicationService, CountryService countryService,
                              DocumentService documentService, VisaService visaService,SenderService senderService){
        this.applicationService = applicationService;
        this.countryService = countryService;
        this.documentService = documentService;
        this.visaService = visaService;
        this.senderService = senderService;
    }

    @GetMapping(value = "/get_applications")
    public List<ApplicationResponseDTO> getApplications() throws ApplicationServiceException {
        List<Application> applications = applicationService.getAllApplications();
        return applications.stream()
                .map(application -> applicationService.convertToDTO(application))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/get_countries")
    public List<CountryDTO> getCountries() throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        return countries.stream()
                .map(country -> countryService.convertToDTO(country))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add_country")
    public void addCountry(@Valid @RequestBody CountryDTO countryDTO) throws CountryServiceException, DuplicateException {
        Country country = countryService.convertToEntity(countryDTO);
        countryService.addCountry(country);
    }

    @PostMapping(value = "/change_country")
    public void changeCountry(@Valid @RequestBody CountryDTO countryDTO) throws CountryServiceException {
        Country country = countryService.convertToEntity(countryDTO);
        countryService.updateCountry(country);
    }

    @DeleteMapping(value = "/delete_country/{country_id}")
    public void deleteCountry(@PathVariable("country_id") Integer countryId) throws CountryServiceException {
        countryService.deleteCountryById(countryId);
    }

    @GetMapping(value = "/get_document_types")
    public List<DocumentTypeDTO> getDocumentTypes() throws DocumentServiceException {
        List<DocumentType> documentTypes = documentService.getAll();
        return documentTypes.stream()
                .map(documentType -> documentService.convertToDTO(documentType))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add_document_type")
    public void addDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) throws DocumentServiceException, DuplicateException {
        DocumentType documentType = documentService.convertToEntity(documentTypeDTO);
        documentService.addDocument(documentType);
    }

    @DeleteMapping(value = "/delete_document_type/{document_type_id}")
    public void deleteDocumentType(@PathVariable("document_type_id") Integer id) throws DocumentServiceException {
        documentService.deleteDocumentById(id);
    }

    @GetMapping(value = "/get_visas")
    public List<VisaResponseDTO> showVisas() throws VisaServiceException {
        List<Visa> visas = visaService.getAll();
        return visas.stream()
                .map(visa -> visaService.convertToDTO(visa))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add_visa")
    public void addVisa(@Valid @RequestBody VisaRequestDTO visaDTO) throws VisaServiceException, DuplicateException, CountryServiceException, DocumentServiceException {
        Visa visa = visaService.convertToEntity(visaDTO);
        visaService.addVisa(visa);
    }

    @PostMapping(value = "/change_visa")
    public void changeVisa(@Valid @RequestBody VisaRequestDTO visaDTO) throws VisaServiceException, CountryServiceException, DocumentServiceException {
        Visa visa = visaService.convertToEntity(visaDTO);
        visaService.updateVisa(visa);
    }

    @DeleteMapping(value = "/delete_visa/{visa_id}")
    public void deleteVisa(@PathVariable("visa_id") Integer id) throws VisaServiceException {
        visaService.deleteVisaById(id);
    }

    @GetMapping(value = "/add_comments/{application_id}")
    public void addCommentsToApplication(@PathVariable("application_id") Integer id,@RequestParam String comments) throws ApplicationServiceException {
        applicationService.addCommentsToApplication(id,comments);
    }

    @GetMapping(value = "/change_result/{application_id}")
    public void changeApplicationResult(@PathVariable("application_id") Integer id,@RequestParam Result result) throws ApplicationServiceException {
        try {
            Application application = applicationService.changeApplicationResultAndStatus(id,result);
            senderService.sendResultToClient(application);
        } catch (SenderServiceException e) {
            Logger.getRootLogger().error("Error of sending result to client",e);
        }
    }

    @GetMapping(value = "/get_statuses")
    public Status[] getStatuses() {
        return Status.values();
    }

    @GetMapping(value = "/get_results")
    public Result[] getResults() {
        return Result.values();
    }
}

