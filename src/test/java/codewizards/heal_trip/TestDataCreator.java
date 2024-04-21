package codewizards.heal_trip;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.requests.department.AddDepartmentRequest;
import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.DTOs.requests.retreat.AddRetreatRequest;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalByIdResponse;
import codewizards.heal_trip.business.abstracts.*;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.HospitalDepartmentDao;
import codewizards.heal_trip.entities.*;
import codewizards.heal_trip.entities.enums.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    private HospitalDepartmentDao hospitalDepartmentDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapperService modelMapperService;

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
        objectMapper.registerModule(new JavaTimeModule());
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
        objectMapper.registerModule(new JavaTimeModule());
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

        for (int i = 0; i < 5; i++) {
//            Patient patient = new Patient();
//            patient.setFirst_name(names.get(i));
//            patient.setLast_name(surnames.get(i));
//            patient.setEmail("patient_" + i + "@gmail.com");
//            patient.setPhone_number("1234567890");
//            patient.setPassword("123456");
//            patient.setBirth_date(java.time.LocalDate.of(2002, 1, 4));
//            patient.setGender(Gender.MALE);
//            patient.setPatient_height(190);
//            patient.setPatient_weight(110);
//            patient.setRoles("PATIENT");
//            patient.setActive(true);
            UserDTO patient = new UserDTO();
            patient.setFirst_name(names.get(i));
            patient.setLast_name(surnames.get(i));
            patient.setEmail("patient_" + i + "@gmail.com");
            patient.setPhone_number("1234567890");
            patient.setPassword("123456");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String patientJson = objectMapper.writeValueAsString(patient);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/auth/register/patient")
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

        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setCity(cities.get(i));
            address.setCountry(countries.get(i));
            address.setAddressDetail(names.get(i));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
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

        for (int i = 1; i <= 5; i++) {

            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/hotelImages/" + i + ".jpeg"));

            HotelImage hotelImage = new HotelImage();
            hotelImage.setImage(fileContent);


            Hotel hotel = new Hotel();
            hotel.setHotelName(names.get(i));
            hotel.setBedCapacity(100);
            hotel.setContactPhone("1234567890");
            hotel.setAddress(addressService.getById(i));

            List<HotelImage> hotelImages = new ArrayList<>();
            hotelImages.add(hotelImage);
            hotel.setHotelImages(hotelImages);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
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
    @Transactional
    @Commit
    void createHotelOrganizer() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 5; i++) {
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
            objectMapper.registerModule(new JavaTimeModule());
            String hotelOrganizerJson = objectMapper.writeValueAsString(hotelOrganizer);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hotelOrganizer/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hotelOrganizerJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(9)
    void createHospital() throws Exception {
        List<String> names = Arrays.asList("Akdeniz University Hospital", "Johns Hopkins Hospital", "Mayo Clinic Hospital",
                "Cleveland Clinic Hospital", "Massachusetts General Hospital", "Atakan Hospital", "Burak Hospital", "Onur Hospital",
                "Sude Hospital", "Aziz Hospital");

        for (int i = 1; i <= 5; i++) {

            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/hospitalImages/" + i + ".jpeg"));

            HospitalImage hospitalImage = new HospitalImage();
            hospitalImage.setImage(fileContent);


            Hospital hospital = new Hospital();
            hospital.setHospitalName(names.get(i));
            hospital.setBed_capacity(1000);
            hospital.setContactPhone("1234567890");
            hospital.setAddress(addressService.getById(i));
            hospital.setActive(true);
            hospital.setDoctors(doctorService.getAllDoctors());
            List<HospitalImage> hospitalImages = new ArrayList<>();
            hospitalImages.add(hospitalImage);
            hospital.setHospitalImages(hospitalImages);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String hospitalJson = objectMapper.writeValueAsString(hospital);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hospital/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hospitalJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(10)
    @Transactional
    @Commit
    void createHospitalOrganizer() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 5; i++) {
            HospitalOrganizer hospitalOrganizer = new HospitalOrganizer();
            hospitalOrganizer.setFirst_name(names.get(i));
            hospitalOrganizer.setLast_name(surnames.get(i));
            hospitalOrganizer.setEmail("hospital_organizer_" + i + "@gmail.com");
            hospitalOrganizer.setPhone_number("1234567890");
            hospitalOrganizer.setPassword("123456");
            hospitalOrganizer.setRoles("HOSPITAL_ORGANIZER");
            hospitalOrganizer.setActive(true);
            GotHospitalByIdResponse hospitalById = hospitalService.getHospitalById(i + 1);
            hospitalOrganizer.setHospital(modelMapperService.forResponse().map(hospitalById, Hospital.class));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String hospitalOrganizerJson = objectMapper.writeValueAsString(hospitalOrganizer);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hospitalOrganizer/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hospitalOrganizerJson));

            result.andExpect(status().isOk());
        }
    }



    @Test
    @Order(11)
    @Transactional
    @Commit
    void createDepartment() throws Exception {
        List<String> names = List.of("Aesthetic Surgery", "Hair Treatments", "Dental Treatments", "Metabolic Surgery", "Eye Diseases");

        List<Hospital> hospitals = hospitalService.getAllHospitals();

        for (String name : names) {
            AddDepartmentRequest department = new AddDepartmentRequest();
            department.setDepartmentName(name);

            List<Integer> hospitalIds = new ArrayList<>();
            for (Hospital hospital : hospitals) {
                hospitalIds.add(hospital.getId());
            }
            department.setHospital_ids(hospitalIds);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
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
    @Transactional
    @Commit
    void createDoctor() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 5; i++) {
//            Doctor doctor = new Doctor();
            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/doctorImages/" + (i + 1) + ".jpeg"));
//            doctor.setDoctorImage(fileContent);
//            doctor.setActive(true);
//            doctor.setDepartment(departmentService.getById(i + 1));
//            doctor.setExperience_year(10);
//            doctor.setDoctorName("Dr. " + names.get(i) + " " + surnames.get(i));
//            GotHospitalByIdResponse hospitalById = hospitalService.getHospitalById(i + 1);
//            doctor.setHospital(modelMapperService.forResponse().map(hospitalById, Hospital.class));
//            doctor.setActive(true);
            CreateDoctorRequest doctor = new CreateDoctorRequest();
            doctor.setDoctorImage(fileContent);
            doctor.setDoctorName("Dr. " + names.get(i) + " " + surnames.get(i));
            doctor.setDepartment_id(i + 1);
            doctor.setHospital_id(i + 1);
            doctor.setExperience_year(10);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String doctorJson = objectMapper.writeValueAsString(doctor);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/doctor/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(doctorJson));
            System.out.println(result.andReturn().getResponse().getContentAsString());

            result.andExpect(status().isOk());
        }
    }

    private RetreatImage saveAestheticRetreatImage(int index) throws Exception {
        byte[] aestheticFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/aesthetic/" + (index) + ".jpg"));
        RetreatImage aestheticRetreatImage = new RetreatImage();
        aestheticRetreatImage.setImage(aestheticFileContent);
        aestheticRetreatImage.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String aestheticRetreatImageJson = objectMapper.writeValueAsString(aestheticRetreatImage);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aestheticRetreatImageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return objectMapper.readValue(response, RetreatImage.class);
    }

    private RetreatImage saveHairRetreatImage(int index) throws Exception {
        byte[] hairFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/hair/" + (index) + ".jpg"));
        RetreatImage hairRetreatImage = new RetreatImage();
        hairRetreatImage.setImage(hairFileContent);
        hairRetreatImage.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String hairRetreatImageJson = objectMapper.writeValueAsString(hairRetreatImage);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hairRetreatImageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return objectMapper.readValue(response, RetreatImage.class);
    }

    private RetreatImage saveDentalRetreatImage(int index) throws Exception {
        byte[] dentalFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/dental/" + (index) + ".jpg"));
        RetreatImage dentalRetreatImage = new RetreatImage();
        dentalRetreatImage.setImage(dentalFileContent);
        dentalRetreatImage.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String dentalRetreatImageJson = objectMapper.writeValueAsString(dentalRetreatImage);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dentalRetreatImageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return objectMapper.readValue(response, RetreatImage.class);
    }

    private RetreatImage saveMetabolicRetreatImage(int index) throws Exception {
        byte[] metabolicFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/metabolic/" + (index) + ".jpg"));
        RetreatImage metabolicRetreatImage = new RetreatImage();
        metabolicRetreatImage.setImage(metabolicFileContent);
        metabolicRetreatImage.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String metabolicRetreatImageJson = objectMapper.writeValueAsString(metabolicRetreatImage);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(metabolicRetreatImageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return objectMapper.readValue(response, RetreatImage.class);
    }

    private RetreatImage saveEyeRetreatImage(int index) throws Exception {
        byte[] eyeFileContent = FileUtils.readFileToByteArray(new File("src/test/retreatImages/eye/" + (index) + ".jpg"));
        RetreatImage eyeRetreatImage = new RetreatImage();
        eyeRetreatImage.setImage(eyeFileContent);
        eyeRetreatImage.setCreateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String eyeRetreatImageJson = objectMapper.writeValueAsString(eyeRetreatImage);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eyeRetreatImageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return objectMapper.readValue(response, RetreatImage.class);
    }

    @Test
    @Order(14)
    @Transactional
    @Commit
    void createRetreat() throws Exception {
        List<String> aesthetic = List.of("Nose", "Breast", "Body", "Face", "Genital");
        List<String> hair = List.of("FUE Hair Transplant", "DHI Hair Transplant", "Afro Hair Transplant", "Beard Transplant", "Eyebrow Transplant");
        List<String> dental = List.of("Dental Crown", "Dental Bridge", "Teeth Whitening", "Tooth Extraction", "Dental Implant");
        List<String> metabolic = List.of("Gastric Bypass", "Gastric Sleeve", "Gastric Balloon");
        List<String> eye = List.of("Lasik Eye Surgery");

        List<List<String>> retreats = List.of(aesthetic, hair, dental, metabolic, eye);



        for (int i = 0; i < retreats.size(); i++) {
            for (int j = 0; j < retreats.get(i).size(); j++) {



//                Retreat retreat = new Retreat();
//                retreat.setRetreat_name(retreats.get(i).get(j));
//                retreat.setDescription(retreats.get(i).get(j));
//                switch (i) {
//                    case 0 -> retreat.setImage(saveAestheticRetreatImage(j + 1));
//                    case 1 -> retreat.setImage(saveHairRetreatImage(j + 1));
//                    case 2 -> retreat.setImage(saveDentalRetreatImage(j + 1));
//                    case 3 -> retreat.setImage(saveMetabolicRetreatImage(j + 1));
//                    case 4 -> retreat.setImage(saveEyeRetreatImage(j + 1));
//                }
//
//                Department department = departmentService.getById(i + 1);
//                retreat.setDepartment(department);
                AddRetreatRequest retreat = new AddRetreatRequest();
                retreat.setRetreat_name(retreats.get(i).get(j));
                retreat.setDescription(retreats.get(i).get(j));
                retreat.setDepartmentId(i + 1);
                switch (i) {
                    case 0 -> retreat.setImageId(saveAestheticRetreatImage(j + 1).getId());
                    case 1 -> retreat.setImageId(saveHairRetreatImage(j + 1).getId());
                    case 2 -> retreat.setImageId(saveDentalRetreatImage(j + 1).getId());
                    case 3 -> retreat.setImageId(saveMetabolicRetreatImage(j + 1).getId());
                    case 4 -> retreat.setImageId(saveEyeRetreatImage(j + 1).getId());
                }


                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
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

        for (int i = 0; i < 5; i++) {
            Feedback feedback = new Feedback();
//            feedback.setBooking(bookingService.getById(i + 1));
            feedback.setComment(comments.get(i));
            feedback.setRating(10 - i);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
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
    @Transactional
    @Commit
    void createBooking() throws Exception {
        for (int i = 0; i < 5; i++) {
//            Booking booking = new Booking();
//            booking.setBooking_date(LocalDate.now());
//            GotHospitalByIdResponse hospitalById = hospitalService.getHospitalById(i + 1);
//            booking.setHospital(modelMapperService.forResponse().map(hospitalById, Hospital.class));
//            booking.setHotel(hotelService.getById(i + 1));
//            DoctorDTOWithHospital doctor = doctorService.getDoctorById(i + 1);
//            booking.setDoctor(modelMapperService.forResponse().map(doctor, Doctor.class));
//            booking.setPatient(patientService.getPatientById(i + 2));
//            booking.setRetreat(retreatService.getRetreatById(i + 1));
//            booking.setStatus("Active");
//            booking.setEndDate(LocalDate.now().plusDays(1));
//            booking.setStartDate(LocalDate.now());

            CreateBookingRequest booking = new CreateBookingRequest();
            booking.setDoctor_id(i + 1);
            booking.setHospital_id(i + 1);
            booking.setEndDate(LocalDate.now().plusDays(i + 1));
            booking.setHotel_id(i + 1);
            booking.setRetreat_id(i + 1);
            booking.setPatient_id(i + 2);
            booking.setStartDate(LocalDate.now());

//            booking.setFeedback(feedbackService.getFeedbackById(i + 1));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String bookingJson = objectMapper.writeValueAsString(booking);
            System.out.println(bookingJson);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/booking/add")
                    .headers(createHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bookingJson));

            result.andExpect(status().isOk());
        }
    }
}
