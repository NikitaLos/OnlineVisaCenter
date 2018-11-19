package com.vironit.onlinevisacenter.converter.clientinfo;

import com.vironit.onlinevisacenter.converter.passport.PassportToPassportResponseDTOConverter;
import com.vironit.onlinevisacenter.dto.clientinfo.ClientInfoResponseDTO;
import com.vironit.onlinevisacenter.entity.ClientInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientInfoToClientInfoResponseDTO implements Converter<ClientInfo, ClientInfoResponseDTO> {

    @Autowired
    private PassportToPassportResponseDTOConverter toPassportResponseDTOConverter;

    @Override
    public ClientInfoResponseDTO convert(ClientInfo clientInfo) {
        ClientInfoResponseDTO clientInfoDTO = new ClientInfoResponseDTO();
        BeanUtils.copyProperties(clientInfo, clientInfoDTO);
        clientInfoDTO.setPassport(toPassportResponseDTOConverter.convert(clientInfo.getPassport()));
        return clientInfoDTO;
    }
}
