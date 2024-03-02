package codewizards.heal_trip;

import codewizards.heal_trip.business.HotelOrganizerService;
import codewizards.heal_trip.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TestDataCreator {

    private final String BASE_URL = "http://localhost:8080";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelOrganizerService hotelOrganizerService;

    @Test
    void createPatient() throws Exception {
        Patient patient = new Patient();
        patient.setFirst_name("Atakan");
        patient.setLast_name("Aksoy");
        patient.setEmail("atkaksoy501@hotmail.com");
        patient.setPhone_number("1234567890");
        patient.setUser_password("123456");
        patient.setBirth_date(java.time.LocalDate.of(2002, 1, 4));
        patient.setGender('M');
        patient.setPatient_height(190);
        patient.setPatient_weight(110);
        patient.setUser_role("patient");
        patient.setActive(true);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String patientJson = objectMapper.writeValueAsString(patient);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/patient/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientJson));

        result.andExpect(status().isOk());
    }

    @Test
    void createAddress() throws Exception {
        Address address = new Address();
        address.setCity("Antalya");
        address.setCountry("Turkey");
        address.setAddressDetail("Akra Hotel");

        ObjectMapper objectMapper = new ObjectMapper();
        String addressJson = objectMapper.writeValueAsString(address);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/address/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(addressJson));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Antalya"))
                .andExpect(jsonPath("$.country").value("Turkey"))
                .andExpect(jsonPath("$.addressDetail").value("Akra Hotel"));
    }

    @Test
    void createHotel() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setHotelName("Akra Hotel");
        hotel.setBedCapacity(100);
        hotel.setContactPhone("1234567890");

        Address address = new Address();
        address.setCity("Antalya");
        address.setCountry("Turkey");
        address.setAddressDetail("Akra Hotel");

        hotel.setAddress(address);

        ObjectMapper objectMapper = new ObjectMapper();
        String hotelJson = objectMapper.writeValueAsString(hotel);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/hotel/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hotelJson));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.hotelName").value("Akra Hotel"))
                .andExpect(jsonPath("$.bedCapacity").value(100))
                .andExpect(jsonPath("$.contactPhone").value("1234567890"))
                .andExpect(jsonPath("$.address.city").value("Antalya"))
                .andExpect(jsonPath("$.address.country").value("Turkey"))
                .andExpect(jsonPath("$.address.addressDetail").value("Akra Hotel"));
    }

    @Test
    void createHotelOrganizer() throws Exception {
        int hotelId = 7;
        HotelOrganizer hotelOrganizer = hotelOrganizerService.createHotelOrganizerWithHotel(hotelId);

        ObjectMapper objectMapper = new ObjectMapper();
        String hotelOrganizerJson = objectMapper.writeValueAsString(hotelOrganizer);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/hotelOrganizer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hotelOrganizerJson));

        result.andExpect(status().isOk());
    }

    @Test
    void createHotelImage() throws Exception {
        int hotelId = 7;
        byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/images/1.jpeg"));

        HotelImage hotelImage = new HotelImage();
        hotelImage.setImage(fileContent);
        hotelImage.setHotel_image_id(hotelId);

        ObjectMapper objectMapper = new ObjectMapper();
        String hotelImageJson = objectMapper.writeValueAsString(hotelImage);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/image/hotel/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hotelImageJson));

        result.andExpect(status().isOk());
    }

    @Test
    void createHospital() throws Exception {
        Hospital hospital = new Hospital();
        hospital.setHospitalName("Akdeniz University Hospital");
        hospital.setBed_capacity(1000);
        hospital.setContactPhone("1234567890");
        hospital.setAddressId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String hospitalJson = objectMapper.writeValueAsString(hospital);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/hospital/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hospitalJson));

        result.andExpect(status().isOk());
    }
}
