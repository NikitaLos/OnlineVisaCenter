package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.entity.*;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.service.ApplicationServiceException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.ApplicationService;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ClientController {

    private CountryService countryService;
    private ApplicationService applicationService;
    private VisaService visaService;
    private UserService userService;

    @Autowired
    public ClientController(CountryService countryService, ApplicationService applicationService,
                            VisaService visaService, UserService userService) {
        this.countryService = countryService;
        this.applicationService = applicationService;
        this.visaService = visaService;
        this.userService = userService;
    }

    @RequestMapping(value = "/compose_application", method = RequestMethod.GET)
    public String composeApplication(Model model) throws CountryServiceException {
        List<Country> countries = countryService.getAll();
        model.addAttribute("countries",countries);
        model.addAttribute("country",new Country());
        return "choose_country_page";
    }

    @RequestMapping(value = "/add_country", method = RequestMethod.POST)
    public String addCountry(Integer countryId, Model model) throws CountryServiceException, VisaServiceException {
        Country country = countryService.getCountry(countryId);
        List<Visa> visas = visaService.getVisasByCountry(country);
        model.addAttribute("visas",visas);
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
        model.addAttribute("price",application.getVisaInfo().getVisa().getPrice());
        return "payment";
    }

    @RequestMapping(value = "/add_client_documents", method = RequestMethod.POST)
    public String addClientDocuments(List<MultipartFile> files, Model model, HttpSession session) {
        Application application = (Application) session.getAttribute("application");
        ClientInfo clientInfo = application.getClientInfo();
        for (MultipartFile file : files){
            //todo
        }
        return "client_document_page";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String pay(Double price,HttpSession session) throws ApplicationServiceException, EntityFindException {
        Application application = (Application) session.getAttribute("application");
        User user = userService.getUser((Integer) session.getAttribute("user_id"));
        application.setUser(user);
        Check check = new Check();
        check.setAmount(price);
        check.setDateOfPayment(LocalDateTime.now());
        check.setPathOnServer("233");
        application.setCheck(check);
        applicationService.addApplicationToQueue(application);
        return "client_document_page";
    }



}
