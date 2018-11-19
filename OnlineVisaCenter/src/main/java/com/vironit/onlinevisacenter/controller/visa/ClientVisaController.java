package com.vironit.onlinevisacenter.controller.visa;

import com.vironit.onlinevisacenter.dto.visa.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import com.vironit.onlinevisacenter.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/visas")
public class ClientVisaController {

    @Autowired
    private VisaService visaService;

    @GetMapping(value = "/country/{id}")
    public List<VisaResponseDTO> getVisasByCountry(@PathVariable("id") Integer id) {
        return visaService.getVisasByCountryId(id);
    }

    @GetMapping(value = "/{id}")
    public VisaResponseDTO getVisasById(@PathVariable("id") Integer id) {
        return visaService.getVisa(id);
    }

    @GetMapping(value = "/aims")
    public AimOfVisit[] getAimsOfVisit() {
        return AimOfVisit.values();
    }
}
