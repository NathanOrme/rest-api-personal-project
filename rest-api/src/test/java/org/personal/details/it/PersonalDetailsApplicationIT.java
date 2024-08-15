package org.personal.details.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.personal.details.model.PersonalDetailsDTO;
import org.personal.details.repository.PersonalDetailsRepository;
import org.personal.details.service.PersonalDetailsService;
import org.personal.details.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("unused")
class PersonalDetailsApplicationIT {

    public static final PersonalDetailsDTO MINIMUM_DTO = PersonalDetailsDTO.builder()
            .customerRef(RandomStringUtils.randomAlphabetic(20))
            .customerName("Doe John")
            .addressLine1("AL1")
            .town("Town")
            .postcode("BF1 BF2")
            .build();

    public static final PersonalDetailsDTO MAXIMUM_DTO = TestUtils.generatePersonDetailsDTO();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonalDetailsService personalDetailsService;

    @Autowired
    PersonalDetailsRepository personalDetailsRepository;

    private ObjectWriter objectWriter;

    /**
     * Creates required-only DTO object and all fields populated
     *
     * @return Stream of Personal Detail DTOs
     */
    public static Stream<PersonalDetailsDTO> testPayloads() {
        return Stream.of(
                MAXIMUM_DTO,
                MINIMUM_DTO
        );
    }

    @BeforeEach
    void setup() {
        personalDetailsRepository.deleteAll();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @ParameterizedTest
    @MethodSource("testPayloads")
    void save_WithValidDTOs_ReturnsCreated(final PersonalDetailsDTO personalDetailsDTO) throws Exception {

        MvcResult result = sendSaveDetailsRequest(personalDetailsDTO);

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void getCustomer_WithExistingCustomerInDatabase_ReturnsOk() throws Exception {
        sendSaveDetailsRequest(MINIMUM_DTO);
        MvcResult result = sendGetRequest(MINIMUM_DTO.getCustomerRef())
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertNotNull(body);

        PersonalDetailsDTO actualDTO = new ObjectMapper().readValue(body, PersonalDetailsDTO.class);
        assertEquals(MINIMUM_DTO.getCustomerRef(), actualDTO.getCustomerRef());
        assertEquals(MINIMUM_DTO.getCustomerName(), actualDTO.getCustomerName());
        assertEquals(MINIMUM_DTO.getAddressLine1(), actualDTO.getAddressLine1());
        assertEquals(MINIMUM_DTO.getAddressLine2(), actualDTO.getAddressLine2());
        assertEquals(MINIMUM_DTO.getTown(), actualDTO.getTown());
        assertEquals(MINIMUM_DTO.getCounty(), actualDTO.getCounty());
        assertEquals(MINIMUM_DTO.getCountry(), actualDTO.getCountry());
        assertEquals(MINIMUM_DTO.getPostcode(), actualDTO.getPostcode());

    }

    @Test
    void getCustomer_WithNonExistingCustomerInDatabase_ReturnsOk() throws Exception {
        MvcResult result = sendGetRequest(MINIMUM_DTO.getCustomerRef())
                .andExpect(status().isNotFound())
                .andReturn();

    }

    private ResultActions sendGetRequest(final String customerRef) throws Exception {
        String url = "/personal-details/get/%s".formatted(customerRef);
        return mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("admin").password("password"))
        );
    }

    private MvcResult sendSaveDetailsRequest(final PersonalDetailsDTO personalDetailsDTO) throws Exception {
        String url = "/personal-details/save";

        return mockMvc.perform(post(url)
                        .content(objectWriter.writeValueAsString(personalDetailsDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("admin").password("password"))
                )
                .andExpect(status().isCreated())
                .andReturn();
    }

}
