package codewizards.heal_trip.controllerTests;

import codewizards.heal_trip.business.DTOs.requests.AddAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.UpdateAddressRequest;
import codewizards.heal_trip.business.DTOs.responses.AddedAddressResponse;
import codewizards.heal_trip.business.DTOs.responses.GotAddressResponse;
import codewizards.heal_trip.business.DTOs.responses.UpdatedAddressResponse;
import codewizards.heal_trip.business.abstracts.IAddressService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.entities.Address;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressTest {
    private final String URL = "http://localhost:8080/address";

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private MockMvc mockMvc;

    private HttpHeaders createLoginHeader() {
        String auth = "admin:admin";
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        return headers;
    }

    @Test
    public void testGetAllAddresses() throws Exception {
        AddAddressRequest address = new AddAddressRequest();
        address.setCity("Istanbul");
        address.setCountry("Turkey");
        address.setAddressDetail("Test Address, Istanbul, Turkey");

        Address addressEntity = modelMapperService.forRequest().map(address, Address.class);
        addressEntity.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(addressEntity);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/add")
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // get saved address id
        String responseBody = result.andReturn().getResponse().getContentAsString();
        AddedAddressResponse savedAddress = objectMapper.readValue(responseBody, AddedAddressResponse.class);

        // get all addresses and check if the address is added
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/getAll")
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON));

        // get the response body
        responseBody = response.andReturn().getResponse().getContentAsString();
        List<GotAddressResponse> addresses = objectMapper.readValue(responseBody, new TypeReference<>() {
        });
        GotAddressResponse gotAddress = addresses.stream().filter(a -> a.getId() == savedAddress.getId()).findFirst().orElse(null);

        assert !addresses.isEmpty();
        assert gotAddress != null;
        assert gotAddress.getCity().equals(savedAddress.getCity());
        assert gotAddress.getCountry().equals(savedAddress.getCountry());
        assert gotAddress.getAddressDetail().equals(savedAddress.getAddressDetail());
    }

    @Test
    public void testAddAddress() throws Exception {
        AddAddressRequest address = new AddAddressRequest();
        address.setCity("Istanbul");
        address.setCountry("Turkey");
        address.setAddressDetail("Test Address, Istanbul, Turkey");

        Address addressEntity = modelMapperService.forRequest().map(address, Address.class);
        addressEntity.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(addressEntity);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/add")
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        String responseBody = result.andReturn().getResponse().getContentAsString();
        AddedAddressResponse savedAddress = objectMapper.readValue(responseBody, AddedAddressResponse.class);

        assert savedAddress != null;
        assert savedAddress.getCity().equals(address.getCity());
        assert savedAddress.getCountry().equals(address.getCountry());
        assert savedAddress.getAddressDetail().equals(address.getAddressDetail());
    }

    @Test
    public void testGetAddressById() throws Exception {
        AddAddressRequest address = new AddAddressRequest();
        address.setCity("Istanbul");
        address.setCountry("Turkey");
        address.setAddressDetail("Test Address, Istanbul, Turkey");

        Address addressEntity = modelMapperService.forRequest().map(address, Address.class);
        addressEntity.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(addressEntity);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/add")
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // get saved address id
        String responseBody = result.andReturn().getResponse().getContentAsString();
        AddedAddressResponse savedAddress = objectMapper.readValue(responseBody, AddedAddressResponse.class);

        // get the address by id
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/get/" + savedAddress.getId())
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON));

        // get the response body
        responseBody = response.andReturn().getResponse().getContentAsString();
        GotAddressResponse gotAddress = objectMapper.readValue(responseBody, GotAddressResponse.class);

        assert gotAddress != null;
        assert gotAddress.getCity().equals(savedAddress.getCity());
        assert gotAddress.getCountry().equals(savedAddress.getCountry());
        assert gotAddress.getAddressDetail().equals(savedAddress.getAddressDetail());
    }

    @Test
    public void testUpdateAddress() throws Exception {
        AddAddressRequest address = new AddAddressRequest();
        address.setCity("Istanbul");
        address.setCountry("Turkey");
        address.setAddressDetail("Test Address, Istanbul, Turkey");

        Address addressEntity = modelMapperService.forRequest().map(address, Address.class);
        addressEntity.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(addressEntity);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/add")
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // get saved address id
        String responseBody = result.andReturn().getResponse().getContentAsString();
        AddedAddressResponse savedAddress = objectMapper.readValue(responseBody, AddedAddressResponse.class);

        // update the address
        address = modelMapperService.forRequest().map(savedAddress, AddAddressRequest.class);
        address.setCity("Ankara");
        address.setCountry("Turkey");
        address.setAddressDetail("Test Address, Ankara, Turkey");
        UpdateAddressRequest updatedAddress = new UpdateAddressRequest();
        updatedAddress.setId(savedAddress.getId());
        updatedAddress.setUpdatedAddress(address);

        String updatedJson = objectMapper.writeValueAsString(updatedAddress);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put(URL + "/update")
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson));

        // get the response body
        responseBody = response.andReturn().getResponse().getContentAsString();
        UpdatedAddressResponse updatedAddressResponse = objectMapper.readValue(responseBody, UpdatedAddressResponse.class);

        assert updatedAddressResponse != null;
        assert updatedAddressResponse.getCity().equals(updatedAddress.getUpdatedAddress().getCity());
        assert updatedAddressResponse.getCountry().equals(updatedAddress.getUpdatedAddress().getCountry());
        assert updatedAddressResponse.getAddressDetail().equals(updatedAddress.getUpdatedAddress().getAddressDetail());
    }

    @Test
    public void testDeleteAddress() throws Exception {
        AddAddressRequest address = new AddAddressRequest();
        address.setCity("Istanbul");
        address.setCountry("Turkey");
        address.setAddressDetail("Test Address, Istanbul, Turkey");

        Address addressEntity = modelMapperService.forRequest().map(address, Address.class);
        addressEntity.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(addressEntity);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/add")
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // get saved address id
        String responseBody = result.andReturn().getResponse().getContentAsString();
        AddedAddressResponse savedAddress = objectMapper.readValue(responseBody, AddedAddressResponse.class);

        // delete the address
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/delete/" + savedAddress.getId())
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON));

        // get all addresses and check if the address is deleted
        response = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/getAll")
                .headers(createLoginHeader())
                .contentType(MediaType.APPLICATION_JSON));

        // get the response body
        responseBody = response.andReturn().getResponse().getContentAsString();
        List<GotAddressResponse> addresses = objectMapper.readValue(responseBody, new TypeReference<>() {
        });
        GotAddressResponse gotAddress = addresses.stream().filter(a -> a.getId() == savedAddress.getId()).findFirst().orElse(null);

        assert gotAddress == null;
    }
}
