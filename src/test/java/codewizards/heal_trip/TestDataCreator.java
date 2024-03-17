package codewizards.heal_trip;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.*;
import codewizards.heal_trip.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class TestDataCreator {

    private final String BASE_URL = "http://localhost:8080";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IHospitalService hospitalService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IHotelService hotelService;
    
    @Autowired
    private IBookingService bookingService;
  
    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IRetreatService retreatService;

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IImageService imageService;

    private HttpHeaders createHeader() {
        // Create the Basic Auth header
        String auth = "admin:admin"; // replace with your username and password
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        return headers;
    }

    @Test
    @Order(1)
    void createAdminUser() throws Exception {
        UserDTO user = new UserDTO();
        user.setFirst_name("Admin");
        user.setLast_name("Admin");
        user.setEmail("admin");
        user.setPassword("admin");
        user.setRoles("ROLE_ADMIN");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        ResultActions result = mockMvc.perform(post(BASE_URL + "/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson));

        result.andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void loginUser() throws Exception {
        // Create a map to hold the username and password
        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("email", "admin");
        loginDetails.put("password", "admin");

        // Convert the map to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String loginDetailsJson = objectMapper.writeValueAsString(loginDetails);

        // Send a POST request to the login endpoint
        ResultActions result = mockMvc.perform(post(BASE_URL + "/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginDetailsJson));

        // Check that the status of the response is OK (200)
        result.andExpect(status().isOk());
    }
    @Test
    @Order(3)
    void createPatient() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 10; i++) {
            Patient patient = new Patient();
            patient.setFirst_name(names.get(i));
            patient.setLast_name(surnames.get(i));
            patient.setEmail("patient_" + i + "@gmail.com");
            patient.setPhone_number("1234567890");
            patient.setPassword("123456");
            patient.setBirth_date(java.time.LocalDate.of(2002, 1, 4));
            patient.setGender('M');
            patient.setPatient_height(190);
            patient.setPatient_weight(110);
            patient.setRoles("PATIENT");
            patient.setActive(true);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String patientJson = objectMapper.writeValueAsString(patient);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/patient/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(patientJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(4)
    void createAddress() throws Exception {
        List<String> cities = List.of("Ankara", "İstanbul", "İzmir", "Antalya", "Adana", "Bursa", "Eskişehir", "Trabzon",
                "Samsun", "Konya");
        List<String> countries = List.of("Turkey", "Germany", "France", "Italy", "Spain", "Portugal", "Greece", "Russia",
                "Ukraine", "England");
        List<String> names = Arrays.asList("Akra Hotel", "Akdeniz University Hospital", "Hilton Hotel", "Johns Hopkins Hospital",
                "Marriott Hotel", "Mayo Clinic Hospital", "Ritz-Carlton Hotel", "Cleveland Clinic Hospital", "Four Seasons Hotel",
                "Massachusetts General Hospital");

        for (int i = 0; i < 10; i++) {
            Address address = new Address();
            address.setCity(cities.get(i));
            address.setCountry(countries.get(i));
            address.setAddressDetail(names.get(i));

            ObjectMapper objectMapper = new ObjectMapper();
            String addressJson = objectMapper.writeValueAsString(address);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/address/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(addressJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(5)
    void createHotel() throws Exception {
        List<String> names = Arrays.asList("Akra Hotel", "Hilton Hotel", "Marriott Hotel", "Ritz-Carlton Hotel",
                "Four Seasons Hotel", "Sheraton Hotel", "Radisson Hotel", "InterContinental Hotel", "Hyatt Hotel",
                "Wyndham Hotel");

        for (int i = 0; i < 10; i++) {
            Hotel hotel = new Hotel();
            hotel.setHotelName(names.get(i));
            hotel.setBedCapacity(100);
            hotel.setContactPhone("1234567890");
            hotel.setAddress(addressService.getById(i + 1));

            ObjectMapper objectMapper = new ObjectMapper();
            String hotelJson = objectMapper.writeValueAsString(hotel);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hotel/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hotelJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(6)
    void createHotelOrganizer() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 10; i++) {
            HotelOrganizer hotelOrganizer = new HotelOrganizer();
            hotelOrganizer.setFirst_name(names.get(i));
            hotelOrganizer.setLast_name(surnames.get(i));
            hotelOrganizer.setEmail("hotel_organizer_" + i + "@gmail.com");
            hotelOrganizer.setPhone_number("1234567890");
            hotelOrganizer.setPassword("123456");
            hotelOrganizer.setRoles("HOTEL_ORGANIZER");
            hotelOrganizer.setActive(true);
            hotelOrganizer.setHotel(hotelService.getById(i + 1));

            ObjectMapper objectMapper = new ObjectMapper();
            String hotelOrganizerJson = objectMapper.writeValueAsString(hotelOrganizer);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hotelOrganizer/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hotelOrganizerJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(7)
    void createHotelImage() throws Exception {
        for (int i = 1; i <= 10; i++) {
            int hotelId = i;
            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/hotelImages/" + i + ".jpeg"));

            HotelImage hotelImage = new HotelImage();
            hotelImage.setImage(fileContent);
            hotelImage.setHotel(hotelService.getById(hotelId));

            ObjectMapper objectMapper = new ObjectMapper();
            String hotelImageJson = objectMapper.writeValueAsString(hotelImage);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/image/hotel/save")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hotelImageJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(8)
    void createHospital() throws Exception {
        List<String> names = Arrays.asList("Akdeniz University Hospital", "Johns Hopkins Hospital", "Mayo Clinic Hospital",
                "Cleveland Clinic Hospital", "Massachusetts General Hospital", "Atakan Hospital", "Burak Hospital", "Onur Hospital",
                "Sude Hospital", "Aziz Hospital");

        for (int i = 0; i < 10; i++) {
            Hospital hospital = new Hospital();
            hospital.setHospitalName(names.get(i));
            hospital.setBed_capacity(1000);
            hospital.setContactPhone("1234567890");
            hospital.setAddress(addressService.getById(i + 1));
            hospital.setActive(true);

            ObjectMapper objectMapper = new ObjectMapper();
            String hospitalJson = objectMapper.writeValueAsString(hospital);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hospital/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hospitalJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(9)
    void createHospitalOrganizer() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 10; i++) {
            HospitalOrganizer hospitalOrganizer = new HospitalOrganizer();
            hospitalOrganizer.setFirst_name(names.get(i));
            hospitalOrganizer.setLast_name(surnames.get(i));
            hospitalOrganizer.setEmail("hospital_organizer_" + i + "@gmail.com");
            hospitalOrganizer.setPhone_number("1234567890");
            hospitalOrganizer.setPassword("123456");
            hospitalOrganizer.setRoles("HOSPITAL_ORGANIZER");
            hospitalOrganizer.setActive(true);
            hospitalOrganizer.setHospital(hospitalService.getHospitalById(i + 1));

            ObjectMapper objectMapper = new ObjectMapper();
            String hospitalOrganizerJson = objectMapper.writeValueAsString(hospitalOrganizer);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hospitalOrganizer/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hospitalOrganizerJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(10)
    void createHospitalImage() throws Exception {
        for (int i = 1; i <= 10; i++) {
            int hospitalId = i;
            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/hospitalImages/" + i + ".jpeg"));

            HospitalImage hospitalImage = new HospitalImage();
            hospitalImage.setImage(fileContent);
            hospitalImage.setHospital(hospitalService.getHospitalById(hospitalId));

            ObjectMapper objectMapper = new ObjectMapper();
            String hospitalImageJson = objectMapper.writeValueAsString(hospitalImage);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/image/hospital/save")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hospitalImageJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(11)
    void createDepartment() throws Exception {
        List<String> names = List.of("Aesthetic and Plastic Surgery", "Hair Treatments", "Dental Treatments", "Metabolic Surgery",
                "Eye Diseases");

        for (int i = 0; i < 5; i++) {
            Department department = new Department();
            department.setDepartmentName(names.get(i));
            department.setHospital(hospitalService.getHospitalById(i +1));

            ObjectMapper objectMapper = new ObjectMapper();
            String departmentJson = objectMapper.writeValueAsString(department);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/department/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(departmentJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(12)
    void createDoctor() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 10; i++) {
            Doctor doctor = new Doctor();
            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/doctorImages/" + (i + 1) + ".jpeg"));
            doctor.setDoctorImage(fileContent);
            doctor.setActive(true);
            doctor.setDepartment(departmentService.getById(i + 1));
            doctor.setExperience_year(10);
            doctor.setDoctorName("Dr. " + names.get(i) + " " + surnames.get(i));
            doctor.setHospital(hospitalService.getHospitalById(i + 1));
            doctor.setActive(true);

            ObjectMapper objectMapper = new ObjectMapper();
            String doctorJson = objectMapper.writeValueAsString(doctor);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/doctor/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(doctorJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(13)
    void createRetreatImage() throws Exception{
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            for (int i = 1; i <= 5; i++) {
                byte[] aestheticFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/aesthetic/" + (i) + ".jpg"));
                byte[] hairFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/hair/" + (i) + ".jpg"));
                byte[] dentalFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/dental/" + (i) + ".jpg"));

                if (i <= 3) {
                    byte[] metabolicFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/metabolic/" + (i) + ".jpg"));
                    RetreatImage metabolicRetreatImage = new RetreatImage();
                    metabolicRetreatImage.setImage(metabolicFileContent);
                    String metabolicRetreatImageJson = objectMapper.writeValueAsString(metabolicRetreatImage);

                    ResultActions metabolicResult = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                            .headers(createHeader())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(metabolicRetreatImageJson));

                    metabolicResult.andExpect(status().isOk());
                }

                if (i == 1) {
                    byte[] eyeFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/eye/" + (i) + ".jpg"));
                    RetreatImage eyeRetreatImage = new RetreatImage();
                    eyeRetreatImage.setImage(eyeFileContent);
                    String eyeRetreatImageJson = objectMapper.writeValueAsString(eyeRetreatImage);

                    ResultActions eyeResult = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                            .headers(createHeader())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(eyeRetreatImageJson));

                    eyeResult.andExpect(status().isOk());
                }

                RetreatImage aestheticRetreatImage = new RetreatImage();
                aestheticRetreatImage.setImage(aestheticFileContent);

                RetreatImage hairRetreatImage = new RetreatImage();
                hairRetreatImage.setImage(hairFileContent);

                RetreatImage dentalRetreatImage = new RetreatImage();
                dentalRetreatImage.setImage(dentalFileContent);


                String aestheticRetreatImageJson = objectMapper.writeValueAsString(aestheticRetreatImage);
                String hairRetreatImageJson = objectMapper.writeValueAsString(hairRetreatImage);
                String dentalRetreatImageJson = objectMapper.writeValueAsString(dentalRetreatImage);


                ResultActions aestheticResult = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aestheticRetreatImageJson));

                ResultActions hairResult = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hairRetreatImageJson));

                ResultActions dentalResult = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dentalRetreatImageJson));


                aestheticResult.andExpect(status().isOk());
                hairResult.andExpect(status().isOk());
                dentalResult.andExpect(status().isOk());
            }
        } catch (NoSuchFileException ignored) {}
    }

    @Test
    @Order(14)
    void createRetreat() throws Exception {
        List<String> aesthetic = List.of("Nose", "Breast", "Body", "Face", "Genital");
        List<String> hair = List.of("FUE Hair Transplant", "DHI Hair Transplant", "Afro Hair Transplant", "Beard Transplant", "Eyebrow Transplant");
        List<String> dental = List.of("Dental Crown", "Dental Bridge", "Teeth Whitening", "Tooth Extraction", "Dental Implant");
        List<String> metabolic = List.of("Gastric Bypass", "Gastric Sleeve", "Gastric Balloon");
        List<String> eye = List.of("Lasik Eye Surgery");

        List<List<String>> retreats = List.of(aesthetic, hair, dental, metabolic, eye);

        int imageId = 1;
        for (int i = 0; i < retreats.size(); i++) {
            for (int j = 0; j < retreats.get(i).size(); j++) {
                Retreat retreat = new Retreat();
                retreat.setRetreat_name(retreats.get(i).get(j));
                retreat.setDescription(retreats.get(i).get(j));
                retreat.setImage(imageService.getRetreatImageById(imageId++));

                Department department = departmentService.getById(i + 1);
                retreat.setDepartment(department);

                ObjectMapper objectMapper = new ObjectMapper();
                String retreatJson = objectMapper.writeValueAsString(retreat);

                ResultActions result = mockMvc.perform(post(BASE_URL + "/retreat/add")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(retreatJson));

                result.andExpect(status().isOk());
            }
        }
    }

    @Test
    @Order(15)
    void createFeedback() throws Exception {
        List<String> comments = List.of("Mükemmel", "Harika", "Çok iyi", "İyi", "Orta", "Kötü", "Çok kötü", "Berbat",
                "Rezalet", "Felaket");

        for (int i = 0; i < 10; i++) {
            Feedback feedback = new Feedback();
            feedback.setBooking(bookingService.getById(i + 1));
            feedback.setComment(comments.get(i));
            feedback.setRating(10 - i);

            ObjectMapper objectMapper = new ObjectMapper();
            String bookingJson = objectMapper.writeValueAsString(feedback);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/feedback/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bookingJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(16)
    void createBooking() throws Exception {
        for (int i = 0; i < 10; i++) {
            Booking booking = new Booking();
            booking.setBooking_date(LocalDate.now());
            booking.setHospital(hospitalService.getHospitalById(i + 1));
            booking.setHotel(hotelService.getById(i + 1));
            booking.setDoctor(doctorService.getDoctorById(i + 1));
            booking.setPatient(patientService.getPatientById(i + 2));
            booking.setRetreat(retreatService.getRetreatById(i + 1));
            booking.setStatus("Active");
            booking.setEndDate(LocalDate.of(2024, 3, 20));
            booking.setStartDate(LocalDate.now());
            booking.setFeedback(feedbackService.getFeedbackById(i + 1));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String bookingJson = objectMapper.writeValueAsString(booking);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/booking/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bookingJson));

            result.andExpect(status().isOk());
        }
    }
}
