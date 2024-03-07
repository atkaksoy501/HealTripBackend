package codewizards.heal_trip;

import codewizards.heal_trip.business.*;
import codewizards.heal_trip.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
    private IDoctorService doctorService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IRetreatService retreatService;

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private IAddressService addressService;

    @Test
    @Order(1)
    void createPatient() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 10; i++) {
            Patient patient = new Patient();
            patient.setFirst_name(names.get(i));
            patient.setLast_name(surnames.get(i));
            patient.setEmail("healtrip.codewizards@gmail.com");
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
    }

    @Test
    @Order(2)
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
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(addressJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(3)
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
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hotelJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(4)
    void createHotelOrganizer() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 10; i++) {
            HotelOrganizer hotelOrganizer = new HotelOrganizer();
            hotelOrganizer.setFirst_name(names.get(i));
            hotelOrganizer.setLast_name(surnames.get(i));
            hotelOrganizer.setEmail("healtrip.codewizards@gmail.com");
            hotelOrganizer.setPhone_number("1234567890");
            hotelOrganizer.setUser_password("123456");
            hotelOrganizer.setUser_role("hotel_organizer");
            hotelOrganizer.setActive(true);
            hotelOrganizer.setHotel(hotelService.getById(i + 1));

            ObjectMapper objectMapper = new ObjectMapper();
            String hotelOrganizerJson = objectMapper.writeValueAsString(hotelOrganizer);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hotelOrganizer/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hotelOrganizerJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(5)
    void createHotelImage() throws Exception {
        for (int i = 1; i <= 10; i++) {
            int hotelId = i;
            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/hotelImages/" + i + ".jpeg"));

            HotelImage hotelImage = new HotelImage();
            hotelImage.setImage(fileContent);
            hotelImage.setHotel_id(hotelId);

            ObjectMapper objectMapper = new ObjectMapper();
            String hotelImageJson = objectMapper.writeValueAsString(hotelImage);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/image/hotel/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hotelImageJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(6)
    void createHospital() throws Exception {
        List<String> names = Arrays.asList("Akdeniz University Hospital", "Johns Hopkins Hospital", "Mayo Clinic Hospital",
                "Cleveland Clinic Hospital", "Massachusetts General Hospital", "Atakan Hospital", "Burak Hospital", "Onur Hospital",
                "Sude Hospital", "Aziz Hospital");

        for (int i = 0; i < 10; i++) {
            Hospital hospital = new Hospital();
            hospital.setHospitalName(names.get(i));
            hospital.setBed_capacity(1000);
            hospital.setContactPhone("1234567890");
            hospital.setAddressId(i + 1);

            ObjectMapper objectMapper = new ObjectMapper();
            String hospitalJson = objectMapper.writeValueAsString(hospital);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hospital/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hospitalJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(7)
    void createHospitalOrganizer() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 10; i++) {
            HospitalOrganizer hospitalOrganizer = new HospitalOrganizer();
            hospitalOrganizer.setFirst_name(names.get(i));
            hospitalOrganizer.setLast_name(surnames.get(i));
            hospitalOrganizer.setEmail("healtrip.codewizards@gmail.com");
            hospitalOrganizer.setPhone_number("1234567890");
            hospitalOrganizer.setUser_password("123456");
            hospitalOrganizer.setUser_role("hospital_organizer");
            hospitalOrganizer.setActive(true);
            hospitalOrganizer.setHospital(hospitalService.getHospitalById(i + 1));

            ObjectMapper objectMapper = new ObjectMapper();
            String hospitalOrganizerJson = objectMapper.writeValueAsString(hospitalOrganizer);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/hospitalOrganizer/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hospitalOrganizerJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(8)
    void createHospitalImage() throws Exception {
        for (int i = 1; i <= 10; i++) {
            int hospitalId = i;
            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/hospitalImages/" + i + ".jpeg"));

            HospitalImage hospitalImage = new HospitalImage();
            hospitalImage.setImage(fileContent);
            hospitalImage.setHospital_id(hospitalId);

            ObjectMapper objectMapper = new ObjectMapper();
            String hospitalImageJson = objectMapper.writeValueAsString(hospitalImage);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/image/hospital/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hospitalImageJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(9)
    void createDepartment() throws Exception {
        List<String> names = List.of("Cardiology", "Dermatology", "Endocrinology", "Gastroenterology", "Hematology",
                "Infectious Disease", "Nephrology", "Neurology", "Oncology", "Pulmonology");

        for (int i = 0; i < 10; i++) {
            Department department = new Department();
            department.setDepartmentName(names.get(i));
            department.setHospital(hospitalService.getHospitalById(i +1));

            ObjectMapper objectMapper = new ObjectMapper();
            String departmentJson = objectMapper.writeValueAsString(department);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/department/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(departmentJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(10)
    void createDoctor() throws Exception {
        List<String> names = List.of("Atakan", "Burak", "Onur", "Sude", "Aziz", "Alp", "Ilgaz", "Süleyman", "Ali", "Mehmet");
        List<String> surnames = List.of("Aksoy", "Erten", "Doğan", "Karaben", "Yolcu", "Aktürk", "Kara", "Keskin", "Kılıç", "Koçak");

        for (int i = 0; i < 10; i++) {
            Doctor doctor = new Doctor();
            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/doctorImages/" + (i + 1) + ".jpeg"));
            doctor.setDoctorImage(fileContent);
            doctor.setActive(true);
            doctor.setDepartmentId(i + 1);
            doctor.setExperience_year(10);
            doctor.setDoctorName("Dr. " + names.get(i) + " " + surnames.get(i));
            doctor.setHospitalId(i + 1);

            ObjectMapper objectMapper = new ObjectMapper();
            String doctorJson = objectMapper.writeValueAsString(doctor);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/doctor/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(doctorJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(11)
    void createRetreat() throws Exception {
        List<String> names = List.of("Diş Dolgusu", "Diş Beyazlatma", "Diş Çekimi", "Kanal Tedavisi", "Ortodonti",
                "Periodontoloji", "Pedodonti", "Ağız ve Diş Cerrahisi", "Endodonti", "Protez");

        for (int i = 0; i < 10; i++) {
            Retreat retreat = new Retreat();
            retreat.setRetreat_name(names.get(i));
            retreat.setDescription(names.get(i));

            Department department = departmentService.getById(i + 1);
            retreat.setDepartment(department);

            ObjectMapper objectMapper = new ObjectMapper();
            String retreatJson = objectMapper.writeValueAsString(retreat);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/retreat/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(retreatJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(12)
    void createFeedback() throws Exception {
        List<String> comments = List.of("Mükemmel", "Harika", "Çok iyi", "İyi", "Orta", "Kötü", "Çok kötü", "Berbat",
                "Rezalet", "Felaket");

        for (int i = 0; i < 10; i++) {
            Feedback feedback = new Feedback();
            feedback.setBookingId(i + 1);
            feedback.setComment(comments.get(i));
            feedback.setRating(10 - i);

            ObjectMapper objectMapper = new ObjectMapper();
            String bookingJson = objectMapper.writeValueAsString(feedback);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/feedback/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bookingJson));

            result.andExpect(status().isOk());
        }
    }

    @Test
    @Order(13)
    void createBooking() throws Exception {
        for (int i = 0; i < 10; i++) {
            Booking booking = new Booking();
            booking.setBooking_date(LocalDate.now());
            booking.setHospital(hospitalService.getHospitalById(i + 1));
            booking.setHotel(hotelService.getById(i + 1));
            booking.setDoctor(doctorService.getDoctorById(i + 1));
            booking.setPatient(patientService.getPatientById(i + 1));
            booking.setRetreat(retreatService.getRetreatById(i + 1));
            booking.setStatus("Active");
            booking.setEndDate(LocalDate.of(2024, 3, 20));
            booking.setStartDate(LocalDate.now());
            booking.setFeedback(feedbackService.getFeedbackById(i + 1));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String bookingJson = objectMapper.writeValueAsString(booking);

            ResultActions result = mockMvc.perform(post(BASE_URL + "/booking/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bookingJson));

            result.andExpect(status().isOk());
        }
    }
}
