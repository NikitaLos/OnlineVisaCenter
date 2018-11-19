package com.vironit.onlinevisacenter.controller.visa;

import com.vironit.onlinevisacenter.dto.visa.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.visa.VisaResponseDTO;
import com.vironit.onlinevisacenter.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employee/visas")
public class EmployeeVisaController {

    @Autowired
    private VisaService visaService;

    @GetMapping
    public List<VisaResponseDTO> showVisas() {
        return visaService.getAll();
    }

    @PostMapping
    public void addVisa(@Valid @RequestBody VisaRequestDTO visaRequestDTO) {
        visaService.addVisa(visaRequestDTO);
    }

    @PatchMapping
    public void changeVisa(@Valid @RequestBody VisaRequestDTO visaRequestDTO) {
        visaService.updateVisa(visaRequestDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteVisa(@PathVariable("id") Integer id) {
        visaService.deleteVisaById(id);
    }
}
