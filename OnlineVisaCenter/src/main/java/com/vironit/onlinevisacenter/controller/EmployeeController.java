package com.vironit.onlinevisacenter.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    private ApplicationService applicationService;
    private CountryService countryService;
    private DocumentService documentService;
    private VisaService visaService;

    @Autowired
    public EmployeeController(ApplicationService applicationService,CountryService countryService,
                              DocumentService documentService,VisaService visaService){
        this.applicationService = applicationService;
        this.countryService = countryService;
        this.documentService = documentService;
        this.visaService = visaService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showEmployeePage(){
        return "WEB-INF/employee_page";
    }

    @RequestMapping(value = "/view_applications",method = RequestMethod.GET)
    public String showApplicationsPage(Model model) throws ApplicationServiceException {
        List<Application> applications = applicationService.getApplications();
        model.addAttribute("applications",applications);
        return "WEB-INF/applications";
    }

    @RequestMapping(value = "/manage_countries",method = RequestMethod.GET)
    public String showCountriesPage(@ModelAttribute("duplicate_message") String duplicateMessage, Model model) throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        model.addAttribute("countries",countries);
        model.addAttribute("duplicate_message",duplicateMessage);
        model.addAttribute("country",new Country());
        return "WEB-INF/countries";
    }

    @RequestMapping(value = "/add_country",method = RequestMethod.POST)
    public String addCountry(@ModelAttribute("country") Country country, RedirectAttributes redirectAttributes) throws CountryServiceException {
        try {
            countryService.addCountry(country);
        } catch (DuplicateException e) {
            redirectAttributes.addAttribute("duplicate_message",e.getMessage());
        }
        return "redirect:/employee/manage_countries";
    }

    @RequestMapping(value = "/delete_country/{country_id}",method = RequestMethod.GET)
    public String deleteCountry(@PathVariable("country_id") Integer countryId) throws UserServiceException {
        countryService.deleteCountryById(countryId);
        return "redirect:/employee/manage_countries";
    }

    @RequestMapping(value = "/manage_document_types",method = RequestMethod.GET)
    public String showDocumentTypesPage(@ModelAttribute("duplicate_message") String duplicateMessage, Model model) throws DocumentServiceException {
        List<DocumentType> documentTypes = documentService.getAll();
        model.addAttribute("document_types",documentTypes);
        model.addAttribute("document_type",new DocumentType());
        return "WEB-INF/documents";
    }

    @RequestMapping(value = "/add_document_type",method = RequestMethod.POST)
    public String addDocumentType(@ModelAttribute("document_type")DocumentType documentType, RedirectAttributes redirectAttributes) throws DocumentServiceException {
        try {
            documentService.addDocument(documentType);
        } catch (DuplicateException e) {
            redirectAttributes.addAttribute("duplicate_message",e.getMessage());
        }
        return "redirect:/employee/manage_document_types";
    }

    @RequestMapping(value = "/delete_document_type/{document_type_id}",method = RequestMethod.GET)
    public String deleteDocumentType(@PathVariable("document_type_id") Integer id) throws DocumentServiceException {
        documentService.deleteDocumentById(id);
        return "redirect:/employee/manage_document_types";
    }
    @RequestMapping(value = "/manage_visas",method = RequestMethod.GET)
    public String showVisasPage(@ModelAttribute("duplicate_message") String duplicateMessage, Model model) throws VisaServiceException, CountryServiceException, DocumentServiceException {
        List<Visa> visas = visaService.getAll();
        List<Country> countries = countryService.getAll();
        List<DocumentType> documentTypes = documentService.getAll();
        model.addAttribute("visas",visas);
        model.addAttribute("countries",countries);
        model.addAttribute("document_types",documentTypes);
        model.addAttribute("visa",new Visa());
        return "WEB-INF/visas";
    }

    @RequestMapping(value = "/add_visa",method = RequestMethod.POST)
    public String addVisa(@ModelAttribute("visa") Visa visa, Integer countryId, int documentTypes[] ,
                           RedirectAttributes redirectAttributes) throws VisaServiceException, CountryServiceException, DocumentServiceException {
        try {
            for (int documentTypeId : documentTypes){
                visa.addDocumentType(documentService.getDocument(documentTypeId));
            }
            Country country = countryService.getCountry(countryId);
            visa.setCountry(country);
            visaService.addVisa(visa);
        } catch (DuplicateException e) {
            redirectAttributes.addAttribute("duplicate_message",e.getMessage());
        }
        return "redirect:/employee/manage_visas";
    }

    @RequestMapping(value = "/delete_visa/{visa_id}",method = RequestMethod.GET)
    public String deleteVisa(@PathVariable("visa_id") Integer id) throws VisaServiceException {
        visaService.deleteVisaById(id);
        return "redirect:/employee/manage_visas";
    }

}

