package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.AbstractTest;
import com.vironit.onlinevisacenter.converter.application.ApplicationRequestDTOToApplicationConverter;
import com.vironit.onlinevisacenter.converter.application.ApplicationToApplicationResponseDTOConverter;
import com.vironit.onlinevisacenter.converter.clientinfo.ClientInfoRequestDTOToClientInfoConverter;
import com.vironit.onlinevisacenter.converter.clientinfo.ClientInfoToClientInfoResponseDTO;
import com.vironit.onlinevisacenter.converter.country.CountryDTOToCountryConverter;
import com.vironit.onlinevisacenter.converter.country.CountryToCountryDTOConverter;
import com.vironit.onlinevisacenter.converter.documenttype.DocumentTypeDTOToDocumentTypeConverter;
import com.vironit.onlinevisacenter.converter.documenttype.DocumentTypeToDocumentTypeDTOConverter;
import com.vironit.onlinevisacenter.converter.passport.PassportRequestDTOToPassportConverter;
import com.vironit.onlinevisacenter.converter.passport.PassportToPassportResponseDTOConverter;
import com.vironit.onlinevisacenter.converter.user.UserDTOToUserConverter;
import com.vironit.onlinevisacenter.converter.user.UserToUserDTOConverter;
import com.vironit.onlinevisacenter.converter.visa.VisaRequestDTOToVisaConverter;
import com.vironit.onlinevisacenter.converter.visa.VisaToVisaResponseDTOConverter;
import com.vironit.onlinevisacenter.converter.visainfo.VisaInfoRequestDTOToVisaInfoConverter;
import com.vironit.onlinevisacenter.converter.visainfo.VisaInfoToVisaInfoResponseDTOConverter;
import com.vironit.onlinevisacenter.dto.application.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.application.ApplicationResponseDTO;
import com.vironit.onlinevisacenter.dto.application.ApplicationReviewDTO;
import com.vironit.onlinevisacenter.dto.clientinfo.ClientInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.clientinfo.ClientInfoResponseDTO;
import com.vironit.onlinevisacenter.dto.country.CountryDTO;
import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
import com.vironit.onlinevisacenter.dto.passport.PassportRequestDTO;
import com.vironit.onlinevisacenter.dto.passport.PassportResponseDTO;
import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.dto.visa.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.visa.VisaResponseDTO;
import com.vironit.onlinevisacenter.dto.visainfo.VisaInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.visainfo.VisaInfoResponseDTO;
import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.ClientInfo;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.Passport;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.entity.VisaInfo;
import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;
import com.vironit.onlinevisacenter.repository.jpa.ApplicationDAO;
import com.vironit.onlinevisacenter.repository.jpa.CountryDAO;
import com.vironit.onlinevisacenter.repository.jpa.DocumentTypeDAO;
import com.vironit.onlinevisacenter.repository.jpa.VisaDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationServiceTest extends AbstractTest {
    @Autowired
    private ApplicationService applicationService;
    @Spy
    private Application application;
    @MockBean
    private ApplicationDAO applicationDAO;
    @MockBean
    private ApplicationToApplicationResponseDTOConverter toApplicationResponseDTOConverter;
    @MockBean
    private ApplicationRequestDTOToApplicationConverter toApplicationConverter;
    @MockBean
    private ClientInfoToClientInfoResponseDTO toClientInfoResponseDTO;
    @MockBean
    private ClientInfoRequestDTOToClientInfoConverter toClientInfoConverter;
    @MockBean
    private VisaInfoToVisaInfoResponseDTOConverter toVisaInfoResponseDTOConverter;
    @MockBean
    private VisaInfoRequestDTOToVisaInfoConverter toVisaInfoConverter;
    @MockBean
    private VisaToVisaResponseDTOConverter toVisaResponseDTOConverter;
    @MockBean
    private VisaRequestDTOToVisaConverter toVisaConverter;
    @MockBean
    private UserToUserDTOConverter toUserDTOConverter;
    @MockBean
    private UserDTOToUserConverter toUserConverter;
    @MockBean
    private DocumentTypeToDocumentTypeDTOConverter toDocumentTypeDTOConverter;
    @MockBean
    private DocumentTypeDTOToDocumentTypeConverter toDocumentTypeConverter;
    @MockBean
    private CountryToCountryDTOConverter toCountryDTOConverter;
    @MockBean
    private CountryDTOToCountryConverter toCountryConverter;
    @MockBean
    private PassportToPassportResponseDTOConverter toPassportResponseDTOConverter;
    @MockBean
    private PassportRequestDTOToPassportConverter toPassportConverter;
    @Mock
    private ApplicationRequestDTO applicationRequestDTO;
    @Mock
    private ApplicationResponseDTO applicationResponseDTO;
    @Mock
    private ApplicationReviewDTO applicationReviewDTO;
    @Mock
    private ClientInfo clientInfo;
    @Mock
    private ClientInfoRequestDTO clientInfoRequestDTO;
    @Mock
    private ClientInfoResponseDTO clientInfoResponseDTO;
    @Mock
    private VisaInfo visaInfo;
    @Mock
    private VisaInfoRequestDTO visaInfoRequestDTO;
    @Mock
    private VisaInfoResponseDTO visaInfoResponseDTO;
    @Mock
    private Passport passport;
    @Mock
    private PassportRequestDTO passportRequestDTO;
    @Mock
    private PassportResponseDTO passportResponseDTO;
    @Mock
    private User user;
    @Mock
    private UserDTO userDTO;
    @Mock
    private Visa visa;
    @Mock
    private VisaRequestDTO visaRequestDTO;
    @Mock
    private VisaResponseDTO visaResponseDTO;
    @Mock
    private Country country;
    @Mock
    private CountryDTO countryDTO;
    @Mock
    private DocumentType documentType;
    @Mock
    private DocumentTypeDTO documentTypeDTO;
    @MockBean
    private CountryDAO countryDAO;
    @MockBean
    private DocumentTypeDAO documentTypeDAO;
    @MockBean
    private VisaDAO visaDAO;

    @Before
    public void setUp() {
        when(toApplicationConverter.convert(applicationRequestDTO)).thenReturn(application);
        when(toApplicationResponseDTOConverter.convert(application)).thenReturn(applicationResponseDTO);
        when(toClientInfoConverter.convert(clientInfoRequestDTO)).thenReturn(clientInfo);
        when(toClientInfoResponseDTO.convert(clientInfo)).thenReturn(clientInfoResponseDTO);
        when(toVisaInfoConverter.convert(visaInfoRequestDTO)).thenReturn(visaInfo);
        when(toVisaInfoResponseDTOConverter.convert(visaInfo)).thenReturn(visaInfoResponseDTO);
        when(toPassportConverter.convert(passportRequestDTO)).thenReturn(passport);
        when(toPassportResponseDTOConverter.convert(passport)).thenReturn(passportResponseDTO);
        when(toVisaConverter.convert(visaRequestDTO)).thenReturn(visa);
        when(toVisaResponseDTOConverter.convert(visa)).thenReturn(visaResponseDTO);
        when(toUserConverter.convert(userDTO)).thenReturn(user);
        when(toUserDTOConverter.convert(user)).thenReturn(userDTO);
        when(toDocumentTypeConverter.convert(documentTypeDTO)).thenReturn(documentType);
        when(toDocumentTypeDTOConverter.convert(documentType)).thenReturn(documentTypeDTO);
        when(toCountryConverter.convert(countryDTO)).thenReturn(country);
        when(toCountryDTOConverter.convert(country)).thenReturn(countryDTO);

        when(application.getId()).thenReturn(1);
        when(application.getUser()).thenReturn(user);
        when(application.getClientInfo()).thenReturn(clientInfo);
        when(application.getVisaInfo()).thenReturn(visaInfo);
        when(clientInfo.getPassport()).thenReturn(passport);
        when(visa.getCountry()).thenReturn(country);
        when(visa.getRequiredDocumentTypes()).thenReturn(Collections.singletonList(documentType));
        when(visaInfo.getVisa()).thenReturn(visa);

        when(applicationRequestDTO.getId()).thenReturn(1);
        when(applicationRequestDTO.getUserDTO()).thenReturn(userDTO);
        when(applicationRequestDTO.getClientInfo()).thenReturn(clientInfoRequestDTO);
        when(applicationRequestDTO.getVisaInfo()).thenReturn(visaInfoRequestDTO);
        when(clientInfoRequestDTO.getPassport()).thenReturn(passportRequestDTO);
        when(visaRequestDTO.getCountryId()).thenReturn(1);
        when(visaRequestDTO.getRequiredDocumentTypesId()).thenReturn(Collections.singletonList(1));
        when(visaInfoRequestDTO.getVisaId()).thenReturn(1);

        when(countryDAO.findById(1)).thenReturn(Optional.of(country));
        when(documentTypeDAO.findById(1)).thenReturn(Optional.of(documentType));
        when(visaDAO.findById(1)).thenReturn(Optional.of(visa));

        when(applicationReviewDTO.getComments()).thenReturn("comments");
        when(applicationReviewDTO.getId()).thenReturn(1);
        when(applicationReviewDTO.getResult()).thenReturn(Result.APPROVE);
    }

    @Test
    public void getApplicationTest() {
        when(applicationDAO.findById(1)).thenReturn(Optional.of(application));
        assertEquals(applicationResponseDTO, applicationService.getApplication(1));
    }

    @Test
    public void addApplicationToQueueTest() {
        doAnswer(invocation -> {
            Application app = invocation.getArgument(0);
            assertEquals(app.getResult(), Result.NO_RESULT);
            assertEquals(app.getStatus(), Status.IN_QUEUE);
            assertNotNull(app.getCreationTime());
            return null;
        }).when(applicationDAO).save(any(Application.class));
        applicationService.addApplicationToQueue(applicationRequestDTO);
    }

    @Test
    public void updateApplicationTest() {
        applicationService.updateApplication(applicationRequestDTO);
        verify(applicationDAO, times(1)).save(application);
    }

    @Test
    public void deleteApplicationFromQueueTest() {
        applicationService.deleteApplicationFromQueue(1);
        verify(applicationDAO, times(1)).deleteById(1);
    }

    @Test
    public void getAllApplicationTest() {
        Application[] applications = {application, application};
        when(applicationDAO.findAll()).thenReturn(Arrays.asList(applications));
        assertEquals(2, applicationService.getAllApplications().size());
    }

    @Test
    public void getUserApplicationsTest() {
        Application[] applications = {application, application};
        when(applicationDAO.findByUserId(1)).thenReturn(Arrays.asList(applications));
        assertEquals(2, applicationService.getUserApplications(1).size());
    }

    @Test
    public void reviewApplicationTest() {
        when(applicationDAO.findById(1)).thenReturn(Optional.of(application));
        doAnswer(invocation -> {
            Application app = invocation.getArgument(0);
            assertEquals(app.getComments(), "comments");
            assertEquals(app.getResult(), Result.APPROVE);
            assertEquals(app.getStatus(), Status.REVIEWED);
            return null;
        }).when(applicationDAO).save(any(Application.class));
        applicationService.reviewApplication(applicationReviewDTO);
    }

}
