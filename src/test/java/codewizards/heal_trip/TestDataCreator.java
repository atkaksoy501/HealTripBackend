package codewizards.heal_trip;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.requests.department.AddDepartmentRequest;
import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.DTOs.requests.hospital.AddHospitalRequest;
import codewizards.heal_trip.business.DTOs.requests.images.AddImageRequestAsBase64;
import codewizards.heal_trip.business.DTOs.requests.retreat.AddRetreatRequest;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalByIdResponse;
import codewizards.heal_trip.business.abstracts.*;
import codewizards.heal_trip.core.converter.ByteToBase64Converter;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.HospitalDepartmentDao;
import codewizards.heal_trip.entities.*;
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
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private IHotelService hotelService;
    
    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IAddressService addressService;

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
        List<String> cities = List.of("Antalya", "Antalya", "Istanbul", "Istanbul", "Izmir",
                "Izmir", "Ankara", "Ankara", "Bodrum", "Bodrum",
                "Antalya", "Istanbul", "Izmir", "Ankara", "Bodrum");

        List<String> hotelNames = Arrays.asList("Akra Hotel", "Rixos Hotel", "Hilton Hotel", "Mariott Hotel",
                "Sheraton Hotel", "Radisson Hotel", "Ritz-Carlton Hotel", "Four Seasons Hotel", "InterContinental Hotel",
                "Hyatt Hotel");

        List<String> hospitalNames = List.of("Medicalpark Hospital", "Acıbadem Hospital", "Medicana Hospital",
                "TOBB ETU Hospital", "Bodrum Amerikan Hospital");

        for (int i = 0; i < 15; i++) {
            Address address = new Address();
            address.setCity(cities.get(i));
            address.setCountry("Turkey");
            if (i < 10) {
                address.setAddressDetail(hotelNames.get(i));
            }
            else {
                address.setAddressDetail(hospitalNames.get(i - 10));
            }


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
        List<String> names = Arrays.asList("Akra Hotel", "Rixos Hotel", "Hilton Hotel", "Mariott Hotel",
                "Sheraton Hotel", "Radisson Hotel", "Ritz-Carlton Hotel", "Four Seasons Hotel", "InterContinental Hotel",
                "Hyatt Hotel");

        List<String> descriptions = Arrays.asList("Akra Hotel is located near Medicalpark Hospital in Antalya, Turkey, offering patients and their families a comfortable and convenient place to stay during their treatment. With luxurious accommodations, modern amenities, and a prime location near the hospital, Akra Hotel provides a peaceful retreat for patients seeking world-class healthcare services. Hotel has a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can enjoy a relaxing stay at Akra Hotel while receiving care at Medicalpark Hospital, knowing that they are in capable hands. Hotel is located in the heart of Antalya, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay.",
                "Rixos Hotel is located near Medicalpark Hospital in Antalya, Turkey, offering patients and their families a luxurious and convenient place to stay during their treatment. With elegant accommodations, modern amenities, and a prime location near the hospital, Rixos Hotel provides a tranquil setting for patients seeking top-notch healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can relax and unwind at Rixos Hotel while undergoing treatment at Medicalpark Hospital, knowing that they are receiving the highest standard of care. The hotel is situated in near the sea, offering stunning views and easy access to the beach, making it an ideal choice for patients looking to enjoy the beauty of Antalya during their stay.",
                "Hilton Hotel is located near Acıbadem Hospital in Istanbul, Turkey, offering patients and their families a luxurious and convenient place to stay during their treatment. With upscale accommodations, modern amenities, and a prime location near the hospital, Hilton Hotel provides a peaceful retreat for patients seeking world-class healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can enjoy a relaxing stay at Hilton Hotel while receiving care at Acıbadem Hospital, knowing that they are in capable hands. The hotel is situated in the heart of Istanbul, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay.",
                "Marriott Hotel is located near Acıbadem Hospital in Istanbul, Turkey, offering patients and their families a comfortable and convenient place to stay during their treatment. With modern accommodations, upscale amenities, and a prime location near the hospital, Marriott Hotel provides a tranquil setting for patients seeking top-notch healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can relax and unwind at Marriott Hotel while undergoing treatment at Acıbadem Hospital, knowing that they are receiving the highest standard of care. The hotel is situated in the heart of Istanbul, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay.",
                "Sheraton Hotel is located near Medicana Hospital in Izmir, Turkey, offering patients and their families a luxurious and convenient place to stay during their treatment. With elegant accommodations, modern amenities, and a prime location near the hospital, Sheraton Hotel provides a peaceful retreat for patients seeking world-class healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can enjoy a relaxing stay at Sheraton Hotel while receiving care at Medicana Hospital, knowing that they are in capable hands. The hotel is situated in the heart of Izmir, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay.",
                "Radisson Hotel is located near Medicana Hospital in Izmir, Turkey, offering patients and their families a comfortable and convenient place to stay during their treatment. With modern accommodations, upscale amenities, and a prime location near the hospital, Radisson Hotel provides a tranquil setting for patients seeking top-notch healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can relax and unwind at Radisson Hotel while undergoing treatment at Medicana Hospital, knowing that they are receiving the highest standard of care. The hotel is situated in the heart of Izmir, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay.",
                "Ritz-Carlton Hotel is located near TOBB ETU Hospital in Ankara, Turkey, offering patients and their families a luxurious and convenient place to stay during their treatment. With upscale accommodations, modern amenities, and a prime location near the hospital, Ritz-Carlton Hotel provides a peaceful retreat for patients seeking world-class healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can enjoy a relaxing stay at Ritz-Carlton Hotel while receiving care at TOBB ETU Hospital, knowing that they are in capable hands. The hotel is situated in the heart of Ankara, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay.",
                "Four Seasons Hotel is located near TOBB ETU Hospital in Ankara, Turkey, offering patients and their families a comfortable and convenient place to stay during their treatment. With luxurious accommodations, modern amenities, and a prime location near the hospital, Four Seasons Hotel provides a tranquil setting for patients seeking top-notch healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can relax and unwind at Four Seasons Hotel while undergoing treatment at TOBB ETU Hospital, knowing that they are receiving the highest standard of care. The hotel is situated in the heart of Ankara, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay.",
                "InterContinental Hotel is located near Bodrum Amerikan Hospital in Bodrum, Turkey, offering patients and their families a luxurious and convenient place to stay during their treatment. With elegant accommodations, modern amenities, and a prime location near the hospital, InterContinental Hotel provides a peaceful retreat for patients seeking world-class healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can enjoy a relaxing stay at InterContinental Hotel while receiving care at Bodrum Amerikan Hospital, knowing that they are in capable hands. The hotel is situated in the heart of Bodrum, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay.",
                "Hyatt Hotel is located near Bodrum Amerikan Hospital in Bodrum, Turkey, offering patients and their families a comfortable and convenient place to stay during their treatment. With modern accommodations, upscale amenities, and a prime location near the hospital, Hyatt Hotel provides a tranquil setting for patients seeking top-notch healthcare services. The hotel features a spa, fitness center, and outdoor pool, as well as a variety of dining options to cater to guests' needs. Patients can relax and unwind at Hyatt Hotel while undergoing treatment at Bodrum Amerikan Hospital, knowing that they are receiving the highest standard of care. The hotel is situated in the heart of Bodrum, close to popular attractions, shopping centers, and dining options, making it an ideal choice for patients looking to explore the city during their stay."
        );

        for (int i = 1; i <= 10; i++) {

            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/hotelImages/" + i + ".jpeg"));

            HotelImage hotelImage = new HotelImage();
            hotelImage.setImage(fileContent);


            Hotel hotel = new Hotel();
            hotel.setHotelName(names.get(i - 1));
            hotel.setBedCapacity(100);
            hotel.setContactPhone("1234567890");
            hotel.setAddress(addressService.getById(i));
            hotel.setDescription(descriptions.get(i - 1));

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
    @Transactional
    @Commit
    void createHospital() throws Exception {
        List<String> names = Arrays.asList("Medicalpark Hospital", "Acıbadem Hospital", "Medicana Hospital",
                "TOBB ETU Hospital", "Bodrum Amerikan Hospital");

        List<String> descriptions = Arrays.asList(
                "Medicalpark is located in Antalya and offers a wide range of medical services, including Aesthetic Surgery, Dental Treatments, Metabolic Surgery, Eye Diseases. The hospital is known for its innovative treatments and cutting-edge technology.",
                "Acıbadem Hospital is located in Istanbul and provides comprehensive medical services, including Aesthetic Surgery, Hair Treatments, Dental Treatments, Metabolic Surgery, Eye Diseases. The hospital is dedicated to excellence in patient care and medical research.",
                "Medicana Hospital is located in Izmir and offers a variety of medical services, including Aesthetic Surgery, Hair Treatments, Dental Treatments. The hospital is known for its commitment to innovation and collaboration in healthcare.",
                "TOBB ETU Hospital is located in Ankara and provides a range of medical services, including Hair Treatments Dental Treatments, Metabolic Surgery, Eye Diseases, Metabolic Surgery and Eye Diseases. The hospital is dedicated to improving patient outcomes and enhancing quality of life.",
                "Bodrum Amerikan Hospital is located in Bodrum and offers a variety of medical services, including Aesthetic Surgery, Hair Treatments Metabolic Surgery, Eye Diseases. The hospital is known for its compassionate care and commitment to patient well-being."
        );

        List<String> longDescriptions = Arrays.asList(
                "Situated in the bustling city of Antalya, Turkey, Medicalpark Hospital has stood as a beacon of quality healthcare since its inception in 2002. Over the years, it has earned a reputation for excellence, offering a comprehensive array of specialized departments aimed at addressing a wide range of medical needs. From Aesthetic Surgery to Hair Treatments, Dental Treatments, Metabolic Surgery, and Eye Diseases, Medicalpark Hospital boasts a diverse range of services, each overseen by a team of skilled professionals dedicated to providing exceptional care.\n" +
                        "Among its standout departments is Aesthetic Surgery, where patients can embark on transformative journeys under the expert guidance of Dr. Atakan Aksoy. Dr. Aksoy's mastery of aesthetic procedures, coupled with his commitment to patient satisfaction, has made this department a sought-after destination for individuals seeking to enhance their appearance and boost their confidence.\n" +
                        "Similarly, Medicalpark's Eye Diseases department, under the leadership of Dr. Alp Aktürk, offers cutting-edge diagnostic and treatment options for various ocular conditions. Dr. Aktürk's expertise and compassionate approach ensure that patients receive the best possible care, whether they require routine eye exams or more complex surgical interventions.\n" +
                        "At Medicalpark Hospital, patient well-being is paramount. Every member of the hospital's staff is dedicated to providing personalized care tailored to meet the unique needs of each individual. From the moment patients enter the facility, they are greeted with warmth and compassion, knowing that they are in capable hands.\n" +
                        "Moreover, Medicalpark Hospital is more than just a healthcare facility; it is a center of innovation and research. Through ongoing collaboration with leading medical professionals and institutions, the hospital remains at the forefront of medical advancements, continuously striving to improve patient outcomes and enhance the quality of care provided.\n" +
                        "As patients navigate their healthcare journey at Medicalpark Hospital, they are supported every step of the way. Whether it's undergoing a surgical procedure, receiving treatment for a chronic condition, or simply seeking routine medical care, patients can trust that they will receive the highest standard of service in a welcoming and supportive environment. With its commitment to excellence and dedication to patient-centered care, Medicalpark Hospital continues to set the standard for quality healthcare in Antalya and beyond.",
                "Located in the vibrant city of Istanbul, Turkey, Acıbadem Hospital has been a cornerstone of healthcare excellence since its establishment in 2008. Boasting a wide array of specialized departments, the hospital is dedicated to providing comprehensive and compassionate care to patients from all walks of life. With departments ranging from Aesthetic Surgery to Hair Treatments, Dental Treatments, Metabolic Surgery, and Eye Diseases, Acıbadem Hospital offers a diverse range of services to meet the evolving healthcare needs of its community.\n" +
                        "Among its standout departments are Hair Treatments and Metabolic Surgery, each led by esteemed specialists in their respective fields. Dr. Burak Erten, renowned for his expertise in hair restoration techniques, leads the Hair Treatments department, where patients can expect personalized care and cutting-edge solutions to address hair loss concerns. Additionally, Dr. Ilgaz Kara heads the Metabolic Surgery department, offering advanced surgical interventions to patients dealing with metabolic disorders. Dr. Kara's dedication to patient care and surgical proficiency have made Acıbadem Hospital a trusted destination for those seeking transformative surgical solutions.\n" +
                        "At Acıbadem Hospital, patient-centric care is at the forefront of everything they do. From the moment patients step through the doors, they are greeted with warmth and compassion by a team of dedicated healthcare professionals committed to providing the highest standard of care. Whether undergoing a cosmetic procedure, seeking treatment for a metabolic condition, or addressing dental or ocular concerns, patients can trust in the expertise and experience of Acıbadem Hospital's staff to guide them through every step of their healthcare journey.\n" +
                        "Moreover, Acıbadem Hospital is not just a place of healing; it is a hub of innovation and research. Through ongoing collaboration with leading medical experts and institutions, the hospital remains at the forefront of medical advancements, continuously striving to improve patient outcomes and enhance the quality of care provided.\n" +
                        "As patients navigate their healthcare journey at Acıbadem Hospital, they can rest assured that they are in capable hands. With a commitment to excellence and a dedication to patient well-being, Acıbadem Hospital continues to set the standard for quality healthcare in Istanbul and beyond.",
                "Nestled in the vibrant city of İzmir, Turkey, Medicana Hospital stands tall as a beacon of excellence in healthcare since its establishment in 2006. Offering a comprehensive range of specialized departments, the hospital is committed to providing top-notch medical care to its patients. From Aesthetic Surgery to Hair Treatments, Dental Treatments, Metabolic Surgery, and Eye Diseases, Medicana Hospital boasts a diverse array of services aimed at addressing various healthcare needs with precision and compassion.\n" +
                        "Among its standout departments are Dental Treatments and Hair Treatments, each led by distinguished specialists renowned for their expertise and commitment to patient care. Dr. Onur Doğan heads the Dental Treatments department, offering a wide range of dental services with a focus on personalized care and patient satisfaction. Additionally, Dr. Süleyman Keskin leads the Hair Treatments department, where patients can expect state-of-the-art solutions for hair restoration and rejuvenation. Dr. Keskin's expertise and dedication have made Medicana Hospital a trusted destination for individuals seeking to enhance their hair health and appearance.\n" +
                        "At Medicana Hospital, patient well-being is paramount. Every member of the hospital's staff is dedicated to providing compassionate care tailored to meet the unique needs of each individual. From the moment patients enter the facility, they are greeted with warmth and empathy, knowing that they are in capable hands.\n" +
                        "Moreover, Medicana Hospital is not just a place of healing; it is a center of innovation and collaboration. Through ongoing research and collaboration with leading medical experts, the hospital remains at the forefront of medical advancements, continuously striving to improve patient outcomes and enhance the quality of care provided.\n" +
                        "As patients embark on their healthcare journey at Medicana Hospital, they can trust that they will receive the highest standard of care in a supportive and welcoming environment. With a commitment to excellence and a dedication to patient satisfaction, Medicana Hospital continues to uphold its reputation as a premier healthcare institution in İzmir and beyond.",
                "Located in the dynamic city of Ankara, Turkey, TOBB ETÜ Hospital has been at the forefront of healthcare innovation since its establishment in 2012. Committed to excellence in patient care, the hospital offers a comprehensive range of specialized departments to meet the diverse healthcare needs of its community. From Aesthetic Surgery to Hair Treatments, Dental Treatments, Metabolic Surgery, and Eye Diseases, TOBB ETÜ Hospital provides cutting-edge services with a focus on compassion and quality.\n" +
                        "Two standout departments at TOBB ETÜ Hospital are Metabolic Surgery and Hair Treatments, each led by distinguished specialists known for their expertise and dedication to patient well-being. Dr. Sude Karaben spearheads the Metabolic Surgery department, offering advanced surgical interventions to patients dealing with metabolic disorders. Dr. Karaben's commitment to excellence and personalized care has made TOBB ETÜ Hospital a trusted destination for individuals seeking effective solutions for metabolic conditions. Additionally, Dr. Ali Kılıç leads the Hair Treatments department, providing innovative solutions for hair restoration and enhancement. Dr. Kılıç's expertise and attention to detail ensure that patients receive the highest standard of care and achieve their desired aesthetic outcomes.\n" +
                        "At TOBB ETÜ Hospital, patient satisfaction is paramount. Every member of the hospital's team is dedicated to providing compassionate care tailored to meet the individual needs of each patient. From the moment patients enter the hospital, they are greeted with warmth and empathy, knowing that they are in capable hands.\n" +
                        "Furthermore, TOBB ETÜ Hospital is committed to advancing medical knowledge and improving patient outcomes through research and collaboration. By staying at the forefront of medical advancements, the hospital continues to provide the highest quality care and treatment options to its patients.\n" +
                        "As patients embark on their healthcare journey at TOBB ETÜ Hospital, they can trust that they will receive exceptional care in a supportive and welcoming environment. With a commitment to excellence and a focus on patient-centered care, TOBB ETÜ Hospital remains a leader in healthcare in Ankara and beyond.",
                "Nestled in the picturesque coastal town of Bodrum, Turkey, Bodrum Amerikan Hospital has quickly established itself as a beacon of excellence in healthcare since its inception in 2016. With a commitment to providing comprehensive and compassionate care, the hospital offers a wide range of specialized departments to meet the diverse healthcare needs of its community. From Aesthetic Surgery to Hair Treatments, Dental Treatments, Metabolic Surgery, and Eye Diseases, Bodrum Amerikan Hospital delivers cutting-edge services with a focus on quality and patient satisfaction.\n" +
                        "Two standout departments at Bodrum Amerikan Hospital are Eye Diseases and Aesthetic Surgery, each led by distinguished specialists renowned for their expertise and dedication to patient care. Dr. Aziz Yolcu Karaben heads the Eye Diseases department, offering advanced diagnostic and treatment options for a variety of ocular conditions. Dr. Karaben's commitment to excellence and patient-centered care has made Bodrum Amerikan Hospital a trusted destination for individuals seeking top-notch eye care. Additionally, Dr. Mehmet Koçak leads the Aesthetic Surgery department, providing transformative procedures to enhance patients' appearance and boost their confidence. Dr. Koçak's skill and artistry ensure that patients receive personalized care and achieve their aesthetic goals with natural-looking results.\n" +
                        "At Bodrum Amerikan Hospital, patient well-being is paramount. Every member of the hospital's staff is dedicated to providing compassionate and personalized care tailored to meet the unique needs of each individual. From the moment patients enter the hospital, they are greeted with warmth and empathy, knowing that they are in skilled and caring hands.\n" +
                        "Furthermore, Bodrum Amerikan Hospital is committed to staying at the forefront of medical advancements through ongoing research and collaboration. By continuously striving to improve patient outcomes and enhance the quality of care provided, the hospital remains dedicated to upholding its reputation as a leader in healthcare in Bodrum and beyond.\n" +
                        "As patients embark on their healthcare journey at Bodrum Amerikan Hospital, they can trust that they will receive exceptional care in a welcoming and supportive environment. With a focus on excellence and a dedication to patient-centered care, Bodrum Amerikan Hospital continues to set the standard for quality healthcare in Bodrum and the surrounding areas."
        );

        for (int i = 1; i <= 5; i++) {

            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/hospitalImages/" + i + ".jpeg"));

            HospitalImage hospitalImage = new HospitalImage();
            hospitalImage.setImage(fileContent);
            hospitalImage.setCreateDate(LocalDateTime.now());

            Hospital hospital = new Hospital();
            hospital.setHospitalName(names.get(i - 1));
            hospital.setDescription(descriptions.get(i - 1));
            hospital.setLongDescription(longDescriptions.get(i - 1));
            hospital.setBed_capacity(1000);
            hospital.setContactPhone("1234567890");
            hospital.setAddress(addressService.getById(i + 10));
            hospital.setActive(true);
            hospital.setCreateDate(LocalDateTime.now());
            hospital.setDoctors(doctorService.getAllDoctors().stream().map(doctor -> modelMapperService.forResponse().map(doctor, Doctor.class)).toList());
            List<HospitalImage> hospitalImages = new ArrayList<>();
            hospitalImage.setHospital(hospital);
            hospitalImages.add(hospitalImage);
            hospital.setHospitalImages(hospitalImages);

//            AddHospitalRequest addHospitalRequest = new AddHospitalRequest();
//            modelMapperService.forRequest().map(hospital, addHospitalRequest);
//            addHospitalRequest.setHospitalImageIds(hospital.getHospitalImages().stream().map(HospitalImage::getId).toList());

            hospitalService.addHospital(hospital);
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

        List<GotHospitalByIdResponse> hospitals = hospitalService.getAllHospitals();

        for (String name : names) {
            AddDepartmentRequest department = new AddDepartmentRequest();
            department.setDepartmentName(name);

            Set<Integer> hospitalIds = new HashSet<>();
            switch (name) {
                case "Aesthetic Surgery" -> {
                    hospitalIds.add(1);
                    hospitalIds.add(2);
                    hospitalIds.add(3);
                    hospitalIds.add(5);
                }
                case "Hair Treatments" -> {
                    hospitalIds.add(2);
                    hospitalIds.add(3);
                    hospitalIds.add(4);
                    hospitalIds.add(5);
                }
                case "Dental Treatments" -> {
                    hospitalIds.add(1);
                    hospitalIds.add(2);
                    hospitalIds.add(3);
                    hospitalIds.add(4);
                }
                case "Metabolic Surgery" -> {
                    hospitalIds.add(1);
                    hospitalIds.add(2);
                    hospitalIds.add(4);
                    hospitalIds.add(5);
                }
                case "Eye Diseases" -> {
                    hospitalIds.add(1);
                    hospitalIds.add(2);
                    hospitalIds.add(4);
                    hospitalIds.add(5);
                }
            }
            department.setHospital_ids(hospitalIds.stream().toList());

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
        List<String> descriptions = List.of("Dr. Atakan Aksoy, a distinguished figure within the Aesthetic Surgery department at Medicalpark Hospital, brings a wealth of experience and a deep academic background to his practice. Hailing from Istanbul, Turkey, Dr. Aksoy was inspired by his family's medical heritage and pursued his passion for medicine from a young age. He completed his undergraduate studies at Istanbul University with distinction before earning his medical degree with honors from Hacettepe University Faculty of Medicine. Dr. Aksoy underwent rigorous training in general surgery at a prestigious medical institution, refining his surgical skills and cultivating a profound commitment to patient care. Following his residency, he pursued specialized training in aesthetic surgery through a highly competitive fellowship program, solidifying his expertise in cosmetic procedures. Dr. Aksoy is dedicated to advancing the field through his extensive research endeavors, contributing to esteemed journals. In his clinical practice, he emphasizes personalized care, tailoring treatments to each patient's individual needs and goals. As a faculty member, Dr. Aksoy is deeply invested in mentoring and educating the next generation of surgeons, fostering a culture of excellence and innovation. Actively involved in professional organizations such as the American Society of Plastic Surgeons and the American Society for Aesthetic Plastic Surgery, Dr. Atakan Aksoy is widely recognized for his clinical excellence and contributions to the field. He embodies the pinnacle of professionalism and expertise in aesthetic surgery, shaping its future through his leadership and dedication.",
                "Dr. Burak Erten, a highly regarded practitioner with over a decade of experience, holds a prominent position in the Hair Treatments department at Acıbadem Hospital. Graduating with honors from Akdeniz University, Dr. Erten has pursued continuous learning and specialization throughout his career. He completed his residency in dermatology at a distinguished medical institution, where he acquired a robust foundation in dermatological care. Dr. Erten furthered his expertise through additional training and specialization in hair treatments, focusing on addressing a wide range of hair-related conditions. His scholarly achievements are underscored by a dedication to research, with numerous publications and presentations in esteemed medical journals and conferences. Known for his patient-centered approach, Dr. Erten ensures personalized treatment plans tailored to the specific needs of each individual. His reputation for excellence has attracted patients from around the globe, including notable figures such as professional athletes seeking his specialized care. In addition to his clinical responsibilities, Dr. Erten is actively engaged in teaching and mentoring medical students, residents, and fellows, imparting his knowledge and expertise to the next generation of healthcare professionals. He maintains active involvement in professional organizations related to dermatology and hair treatments, remaining at the forefront of advancements in the field. Dr. Burak Erten's unwavering commitment to excellence in hair treatments, coupled with his extensive academic background and clinical experience, establishes him as a trusted authority at Acıbadem Hospital and within the broader medical community.",
                "Dr. Onur Doğan is a highly respected practitioner in the Dental Treatments department at Medicana Hospital, renowned for his expertise and dedication to providing exceptional dental care. Graduating with top honors from Koç University's esteemed dental school, Dr. Doğan pursued advanced training in various dental specialties, honing his skills in diagnosis, treatment, and management of oral health issues. With years of experience in the field, he has established himself as a leader in dental treatments, offering a comprehensive range of services to his patients. Dr. Doğan's commitment to excellence extends beyond his clinical practice; he actively contributes to dental research, with several publications in reputable journals. Patients from all walks of life, including high-profile individuals and celebrities, seek out Dr. Doğan's services for his exceptional care and personalized approach. As a faculty member, he is deeply invested in education and mentorship, guiding the next generation of dental professionals. Dr. Doğan remains at the forefront of advancements in dental treatments, staying actively engaged in professional organizations and continuing education activities. His unwavering dedication to patient care and clinical excellence makes him a valued member of the Medicana Hospital and a trusted resource in the field of dental treatments.",
                "Dr. Sude Karaben is a distinguished practitioner in the Metabolic Surgery department at TOBB ETU Hospital, recognized for her expertise and commitment to improving patients' lives through innovative surgical interventions. Graduating with top honors from Antalya Bilim University's esteemed medical school, Dr. Karaben pursued advanced training in metabolic surgery, specializing in procedures aimed at treating obesity and metabolic disorders. With years of experience in the field, she has become a leading authority in metabolic surgery, offering state-of-the-art treatments to her patients. Dr. Karaben's dedication to advancing the field is evident through her active involvement in research, with numerous publications and presentations in prestigious medical forums. Patients from around the world seek out Dr. Karaben's expertise for her compassionate care and outstanding outcomes. As a faculty member, she is deeply committed to educating and mentoring the next generation of surgeons, imparting her knowledge and skills to medical students, residents, and fellows. Dr. Karaben remains at the forefront of advancements in metabolic surgery, actively participating in professional organizations and continuing education activities. Her unwavering dedication to patient care and clinical excellence makes her a valued member of TOBB ETU Hospital and a trusted leader in the field of metabolic surgery.",
                "Dr. Aziz Yolcu is a distinguished practitioner in the Eye Diseases department at Bodrum Amerikan Hospital, renowned for his expertise and commitment to preserving and restoring vision for his patients. Graduating with top honors from Antalya Bilim University's esteemed medical school, Dr. Yolcu pursued advanced training in ophthalmology, specializing in the diagnosis and treatment of various eye diseases. With years of experience in the field, he has established himself as a leading authority in ophthalmology, offering comprehensive eye care services to his patients. Dr. Yolcu's dedication to advancing the field is evident through his active involvement in research, with numerous publications and presentations in reputable medical journals and conferences. Patients from all walks of life seek out Dr. Yolcu's expertise for his compassionate care and exceptional outcomes. As a faculty member, he is deeply committed to educating and mentoring the next generation of ophthalmologists, sharing his knowledge and skills with medical students, residents, and fellows. Dr. Yolcu remains at the forefront of advancements in eye diseases, actively participating in professional organizations and continuing education activities. His unwavering dedication to patient care and clinical excellence makes him a valued member of Bodrum Amerikan Hospital and a trusted leader in the field of eye diseases.",
                "Dr. Alp Aktürk is a distinguished practitioner in the Eye Diseases department at Medicalpark Hospital, renowned for his expertise and unwavering commitment to providing exemplary eye care to his patients. Graduating with top honors from the Faculty of Medicine at Istanbul University, Dr. Aktürk pursued advanced training in ophthalmology, specializing in the diagnosis and treatment of various eye conditions. With extensive experience in the field, he has established himself as a trusted authority in ophthalmology, delivering comprehensive eye care services with compassion and precision. Dr. Aktürk's dedication to advancing the field is evident through his active participation in research, with numerous publications and presentations in esteemed medical journals and conferences. Patients from diverse backgrounds seek out Dr. Aktürk's expertise for his exceptional care and outstanding treatment outcomes. As a committed educator, he actively mentors and guides medical students, residents, and fellows, sharing his wealth of knowledge and experience in ophthalmology. Dr. Aktürk remains at the forefront of advancements in eye diseases, actively engaging in professional organizations and continuing education activities to provide cutting-edge care to his patients. His steadfast dedication to patient well-being and clinical excellence solidifies his position as a valued member of Medicalpark Hospital and a respected leader in the field of eye diseases.",
                "Dr. Ilgaz Kara is a distinguished practitioner in the Metabolic Surgery department at Acıbadem Hospital, renowned for his expertise and commitment to improving patients' lives through innovative surgical interventions. Graduating with top honors from the Faculty of Medicine at Hacettepe University, Dr. Kara pursued advanced training in metabolic surgery, specializing in procedures aimed at treating obesity and metabolic disorders. With years of experience in the field, he has become a leading authority in metabolic surgery, offering state-of-the-art treatments to his patients. Dr. Kara's dedication to advancing the field is evident through his active involvement in research, with numerous publications and presentations in prestigious medical forums. Patients from around the world seek out Dr. Kara's expertise for his compassionate care and outstanding outcomes. As a faculty member, he is deeply committed to educating and mentoring the next generation of surgeons, imparting his knowledge and skills to medical students, residents, and fellows. Dr. Kara remains at the forefront of advancements in metabolic surgery, actively participating in professional organizations and continuing education activities. His unwavering dedication to patient care and clinical excellence makes him a valued member of Acıbadem Hospital and a trusted leader in the field of metabolic surgery.",
                "Dr. Süleyman Keskin is a distinguished practitioner in the Hair Treatments department at Medicana Hospital, renowned for his expertise and dedication to providing exceptional care to his patients. Graduating with top honors from the Faculty of Medicine at Ege University, Dr. Keskin pursued advanced training in dermatology, specializing in the diagnosis and treatment of various skin and hair conditions. With years of experience in the field, he has established himself as a trusted authority in hair treatments, offering a comprehensive range of services to his patients. Dr. Keskin's commitment to excellence is evident through his active involvement in research, with numerous publications and presentations in reputable medical journals and conferences. Patients from all walks of life seek out Dr. Keskin's expertise for his compassionate care and outstanding outcomes. As a faculty member, he is deeply committed to educating and mentoring the next generation of dermatologists, sharing his knowledge and skills with medical students, residents, and fellows. Dr. Keskin remains at the forefront of advancements in hair treatments, actively participating in professional organizations and continuing education activities. His unwavering dedication to patient care and clinical excellence makes him a valued member of Medicana Hospital and a trusted leader in the field of hair treatments.",
                "Dr. Ali Kılıç is a distinguished practitioner in the Hair Treatments department at TOBB ETU Hospital, renowned for his expertise and commitment to providing exceptional care to his patients. Graduating with top honors from the Faculty of Medicine at Hacettepe University, Dr. Kılıç pursued advanced training in dermatology, specializing in the diagnosis and treatment of various skin and hair conditions. With years of experience in the field, he has established himself as a trusted authority in hair treatments, offering a comprehensive range of services to his patients. Dr. Kılıç's dedication to excellence is evident through his active involvement in research, with numerous publications and presentations in reputable medical journals and conferences. Patients from all walks of life seek out Dr. Kılıç's expertise for his compassionate care and outstanding outcomes. As a faculty member, he is deeply committed to educating and mentoring the next generation of dermatologists, sharing his knowledge and skills with medical students, residents, and fellows. Dr. Kılıç remains at the forefront of advancements in hair treatments, actively participating in professional organizations and continuing education activities. His unwavering dedication to patient care and clinical excellence makes him a valued member of TOBB ETU Hospital and a trusted leader in the field of hair treatments.",
                "Dr. Mehmet Koçak is a distinguished practitioner in the Aesthetic Surgery department at Bodrum Amerikan Hospital, renowned for his expertise and commitment to providing exceptional care to his patients. Graduating with top honors from the Faculty of Medicine at Ege University, Dr. Koçak pursued advanced training in plastic surgery, specializing in a wide range of aesthetic procedures. With years of experience in the field, he has established himself as a trusted authority in aesthetic surgery, offering a comprehensive range of services to his patients. Dr. Koçak's dedication to excellence is evident through his active involvement in research, with numerous publications and presentations in reputable medical journals and conferences. Patients from all walks of life seek out Dr. Koçak's expertise for his compassionate care and outstanding outcomes. As a faculty member, he is deeply committed to educating and mentoring the next generation of plastic surgeons, sharing his knowledge and skills with medical students, residents, and fellows. Dr. Koçak remains at the forefront of advancements in aesthetic surgery, actively participating in professional organizations and continuing education activities. His unwavering dedication to patient care and clinical excellence makes him a valued member of Bodrum Amerikan Hospital and a trusted leader in the field of aesthetic surgery."
        );

        List<Integer> departmentIds = Arrays.asList(1, 2, 3, 4, 5, 5, 4, 2, 2, 1);

        for (int i = 0; i < 10; i++) {
            byte[] fileContent = Files.readAllBytes(Paths.get("src/test/doctorImages/" + (i + 1) + ".jpeg"));
            CreateDoctorRequest doctor = new CreateDoctorRequest();
            doctor.setDoctorImage(ByteToBase64Converter.convert(fileContent));
            doctor.setDoctorName("Dr. " + names.get(i) + " " + surnames.get(i));
            if (i > 4) {
                doctor.setHospital_id(i - 4);
            } else {
                doctor.setHospital_id(i + 1);
            }
            doctor.setDepartment_id(departmentIds.get(i));
            doctor.setExperience_year(10);
            doctor.setDescription(descriptions.get(i));

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

    private Integer saveAestheticRetreatImage(int index) throws Exception {
        byte[] aestheticFileContent = Files.readAllBytes(Paths.get("src/test/retreatImages/aesthetic/" + (index) + ".jpg"));
        AddImageRequestAsBase64 image = new AddImageRequestAsBase64();
        image.setImage(ByteToBase64Converter.convert(aestheticFileContent));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String imageJson = objectMapper.writeValueAsString(image);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();
        return Integer.parseInt(response);
    }

    private Integer saveHairRetreatImage(int index) throws Exception {
        byte[] hairFileContent = Files.readAllBytes(Paths.get("src/test/retreatImages/hair/" + (index) + ".jpg"));
        AddImageRequestAsBase64 image = new AddImageRequestAsBase64();
        image.setImage(ByteToBase64Converter.convert(hairFileContent));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String imageJson = objectMapper.writeValueAsString(image);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return Integer.parseInt(response);
    }

    private Integer saveDentalRetreatImage(int index) throws Exception {
        byte[] dentalFileContent = Files.readAllBytes(Paths.get("src/test/retreatImages/dental/" + (index) + ".jpg"));
        AddImageRequestAsBase64 image = new AddImageRequestAsBase64();
        image.setImage(ByteToBase64Converter.convert(dentalFileContent));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String imageJson = objectMapper.writeValueAsString(image);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return Integer.parseInt(response);
    }

    private Integer saveMetabolicRetreatImage(int index) throws Exception {
        byte[] metabolicFileContent = Files.readAllBytes(Paths.get("src/test/retreatImages/metabolic/" + (index) + ".jpg"));
        AddImageRequestAsBase64 image = new AddImageRequestAsBase64();
        image.setImage(ByteToBase64Converter.convert(metabolicFileContent));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String imageJson = objectMapper.writeValueAsString(image);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return Integer.parseInt(response);
    }

    private Integer saveEyeRetreatImage(int index) throws Exception {
        byte[] eyeFileContent = Files.readAllBytes(Paths.get("src/test/retreatImages/eye/" + (index) + ".jpg"));
        AddImageRequestAsBase64 image = new AddImageRequestAsBase64();
        image.setImage(ByteToBase64Converter.convert(eyeFileContent));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String imageJson = objectMapper.writeValueAsString(image);

        MvcResult result = mockMvc.perform(post(BASE_URL + "/image/retreat/save")
                        .headers(createHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        return Integer.parseInt(response);
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

        List<String> aestheticDesc = List.of("Enhance your facial harmony with our nose treatments. Whether you're seeking refinement, reshaping, or reconstruction, our expert team ensures your nasal aesthetics align perfectly with your desired look and functional needs.",
                "Achieve the silhouette you've always desired with our comprehensive breast treatments. From augmentation to reduction, lift to reconstruction, we tailor each procedure to your unique goals, ensuring natural-looking results and boosted confidence.",
                "Sculpt your body to perfection with our range of body contouring treatments. Whether it's eliminating stubborn fat, tightening loose skin, or enhancing muscle definition, we offer personalized solutions to help you achieve your ideal physique.",
                "Rediscover youthful radiance and confidence with our facial rejuvenation treatments. From facelifts to fillers, laser resurfacing to skincare, our expert team customizes each approach to address your specific concerns and unveil your natural beauty.",
                "Enhance intimacy and confidence with our genital treatments. From rejuvenation to enhancement, our discreet and personalized approach ensures natural-looking results and improved self-esteem.");
        List<String> hairDesc = List.of("Reclaim a full head of hair with our advanced FUE hair transplant procedure. Using the latest techniques, we restore density and natural-looking hairlines, giving you renewed confidence and a more youthful appearance.",
                "Experience seamless hair restoration with our DHI hair transplant technique. By implanting individual hair follicles directly into the scalp, we ensure a natural-looking result with minimal downtime and maximum satisfaction.",
                "Embrace your natural beauty with our specialized Afro hair transplant procedure. Designed to cater to unique hair textures and patterns, we restore fullness and confidence while preserving your distinct identity.",
                "Define your masculinity with our beard transplant procedure. Whether you desire a fuller beard, filling in patches, or sculpting a specific style, our expert team delivers natural-looking results tailored to your preferences.",
                "Frame your face with perfectly arched eyebrows through our eyebrow transplant procedure. Using advanced techniques, we restore symmetry and definition to your brows, enhancing your overall facial aesthetics.");
        List<String> dentalDesc = List.of("Restore strength, function, and aesthetics to your smile with our dental crown procedure. Custom-crafted to blend seamlessly with your natural teeth, our crowns ensure durability and a confident smile.",
                "Regain the ability to eat, speak, and smile comfortably with our dental bridge solution. Designed to fill gaps left by missing teeth, our bridges restore dental function and aesthetics for a complete and confident smile.",
                "Illuminate your smile with our professional teeth whitening treatment. Using safe and effective techniques, we brighten your teeth several shades, restoring confidence and leaving you with a radiant smile.",
                "Resolve dental issues and alleviate discomfort with our tooth extraction service. Our gentle approach ensures a smooth and comfortable procedure, prioritizing your oral health and well-being.",
                "Replace missing teeth with our state-of-the-art dental implant procedure. Offering a permanent solution that mimics the look and function of natural teeth, we restore your smile and confidence for years to come.");
        List<String> metabolicDesc = List.of("Take control of your weight and health with our gastric bypass procedure. By rerouting the digestive system, we help you achieve significant and sustainable weight loss, leading to improved overall well-being.",
                "Transform your life with our gastric sleeve surgery. By reducing stomach size and limiting food intake, we empower you to achieve substantial weight loss and embrace a healthier lifestyle.",
                "Kickstart your weight loss journey with our gastric balloon procedure. By temporarily occupying space in the stomach, the balloon aids in portion control and jumpstarts healthy habits for long-term success.");
        List<String> eyeDesc = List.of("See the world with newfound clarity through our Lasik eye surgery. By reshaping the cornea with precision and accuracy, we correct refractive errors and reduce dependency on glasses or contact lenses, offering freedom and convenience.");

        List<List<String>> retreatsDesc = List.of(aestheticDesc, hairDesc, dentalDesc, metabolicDesc, eyeDesc);

        for (int i = 0; i < retreats.size(); i++) {
            for (int j = 0; j < retreats.get(i).size(); j++) {
                AddRetreatRequest retreat = new AddRetreatRequest();
                retreat.setRetreat_name(retreats.get(i).get(j));
                retreat.setDescription(retreatsDesc.get(i).get(j));
                retreat.setDepartmentId(i + 1);
                switch (i) {
                    case 0 -> retreat.setImageId(saveAestheticRetreatImage(j + 1));
                    case 1 -> retreat.setImageId(saveHairRetreatImage(j + 1));
                    case 2 -> retreat.setImageId(saveDentalRetreatImage(j + 1));
                    case 3 -> retreat.setImageId(saveMetabolicRetreatImage(j + 1));
                    case 4 -> retreat.setImageId(saveEyeRetreatImage(j + 1));
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
