package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    CountryService countryService;

    @Autowired
    VisaService visaService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/compose_application", method = RequestMethod.GET)
    public String composeApplication(Model model) throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        model.addAttribute("countries",countries);
        return "choose_country_page";
    }

    @RequestMapping(value = "/add_country", method = RequestMethod.POST)
    public String addCountry(Integer countryId, Model model) throws CountryServiceException {
        Country country = countryService.getCountryEager(countryId);
        country.getAvailableVisas().size();
        model.addAttribute("country",country);
        return "visa_info_page";
    }

    @RequestMapping(value = "/add_visa_info", method = RequestMethod.POST)
    public String addVisaInfo(VisaInfo visaInfo, String from, String to, Integer visaId, Model model, HttpSession session) throws  VisaServiceException {
        Visa visa = visaService.getVisaEager(visaId);
        Application application = new Application();
        visaInfo.setVisa(visa);
        visaInfo.setDateTo(LocalDate.parse(to));
        visaInfo.setDateFrom(LocalDate.parse(from));
        application.setVisaInfo(visaInfo);
        model.addAttribute("aims",AimOfVisit.values());
        session.setAttribute("application",application);
        return "client_info_page";
    }

    @RequestMapping(value = "/add_client_info", method = RequestMethod.POST)
    public String addClientInfo(ClientInfo clientInfo,Passport passport, String dateBirth, String dateReceiving,
                          String dateEnding, String aim, Model model, HttpSession session) {
        clientInfo.setDateOfBirth(LocalDate.parse(dateBirth));
        clientInfo.setAimOfVisit(AimOfVisit.valueOf(aim));
        passport.setDateOfReceiving(LocalDate.parse(dateReceiving));
        passport.setDateOfEnding(LocalDate.parse(dateEnding));
        clientInfo.setPassport(passport);
        Application application = (Application) session.getAttribute("application");
        application.setClientInfo(clientInfo);
        session.setAttribute("application",application);
        model.addAttribute("document_types",application.getVisaInfo().getVisa().getRequiredDocumentTypes());
        model.addAttribute("client_info",clientInfo);
        return "client_document_page";
    }

    @RequestMapping(value = "/add_client_documents", method = RequestMethod.POST)
    public String addClientDocuments(List<MultipartFile> files, Model model, HttpSession session) {
        System.out.println(files.size());
        return "client_document_page";
    }


}
