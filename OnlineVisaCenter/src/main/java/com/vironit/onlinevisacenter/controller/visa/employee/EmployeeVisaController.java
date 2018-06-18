package com.vironit.onlinevisacenter.controller.visa.employee;

import com.vironit.onlinevisacenter.dto.converter.VisaConverter;
import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.response.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.ConverterException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeVisaController {

    private VisaService visaService;
    private VisaConverter visaConverter;

    @Autowired
    public EmployeeVisaController(VisaService visaService, VisaConverter visaConverter) {
        this.visaService = visaService;
        this.visaConverter = visaConverter;
    }

    @GetMapping(value = "/get_visas")
    public List<VisaResponseDTO> showVisas() throws ServiceException {
        List<Visa> visas = visaService.getAll();
        return visas.stream()
                .map(visa -> visaConverter.convertToDTO(visa))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add_visa")
    public void addVisa(@Valid @RequestBody VisaRequestDTO visaDTO) throws ServiceException, ConverterException {
        Visa visa = visaConverter.convertToEntity(visaDTO);
        visaService.addVisa(visa);
    }

    @PostMapping(value = "/change_visa")
    public void changeVisa(@Valid @RequestBody VisaRequestDTO visaDTO) throws ServiceException, ConverterException {
        Visa visa = visaConverter.convertToEntity(visaDTO);
        visaService.updateVisa(visa);
    }

    @DeleteMapping(value = "/delete_visa/{visa_id}")
    public void deleteVisa(@PathVariable("visa_id") Integer id) throws ServiceException {
        visaService.deleteVisaById(id);
    }
}
