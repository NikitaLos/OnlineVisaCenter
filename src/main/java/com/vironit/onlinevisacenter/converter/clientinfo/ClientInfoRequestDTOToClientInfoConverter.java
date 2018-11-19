package com.vironit.onlinevisacenter.converter.clientinfo;

import com.vironit.onlinevisacenter.converter.passport.PassportRequestDTOToPassportConverter;
import com.vironit.onlinevisacenter.dto.clientinfo.ClientInfoRequestDTO;
import com.vironit.onlinevisacenter.entity.ClientInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientInfoRequestDTOToClientInfoConverter implements Converter<ClientInfoRequestDTO, ClientInfo> {

    @Autowired
    private PassportRequestDTOToPassportConverter toPassportConverter;

    @Override
    public ClientInfo convert(ClientInfoRequestDTO clientInfoRequestDTO) {
        ClientInfo clientInfo = new ClientInfo();
        BeanUtils.copyProperties(clientInfoRequestDTO, clientInfo);
        clientInfo.setPassport(toPassportConverter.convert(clientInfoRequestDTO.getPassport()));

        return clientInfo;
    }
}
