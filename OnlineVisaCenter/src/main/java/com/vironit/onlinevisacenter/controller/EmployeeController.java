package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import com.vironit.onlinevisacenter.service.inrefaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    public EmployeeController(ApplicationService applicationService,CountryService countryService,DocumentService documentService) {
        this.applicationService = applicationService;
        this.countryService = countryService;
        this.documentService = documentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminPage(){
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
        return "WEB-INF/countries";
    }

    @RequestMapping(value = "/delete_country/{country_id}",method = RequestMethod.GET)
    public String deleteCountry(@PathVariable("country_id") Integer countryId) throws UserServiceException {
        countryService.deleteCountryById(countryId);
        return "redirect:/employee/manage_countries";
    }

    @RequestMapping(value = "/add_country",method = RequestMethod.POST)
    public String addCountry(Country country, RedirectAttributes redirectAttributes) throws CountryServiceException {
        try {
            countryService.addCountry(country);
        } catch (DuplicateException e) {
            redirectAttributes.addAttribute("duplicate_message",e.getMessage());
        }
        return "redirect:/employee/manage_countries";
    }

//    @RequestMapping(value = "/manage_documents",method = RequestMethod.GET)
//    public String showDocumentsPage(@ModelAttribute("duplicate_message") String duplicateMessage, Model model) throws CountryServiceException {
//        List<DocumentType> documentTypes = d.getAll();
//        model.addAttribute("countries",countries);
//        model.addAttribute("duplicate_message",duplicateMessage);
//        return "WEB-INF/countries";
//    }
}
