package codewizards.heal_trip.business.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.responses.booking.CreatedBookingResponse;
import codewizards.heal_trip.business.DTOs.responses.retreat.GetRetreatByIdResponse;
import codewizards.heal_trip.business.abstracts.*;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.entities.*;
import codewizards.heal_trip.entities.enums.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.*;

@Service
public class BookingService implements IBookingService {
    private final BookingDao bookingDao;
    private final ModelMapperService modelMapperService;
    private final IPatientService patientService;
    private final IHospitalService hospitalService;
    private final IHotelService hotelService;
    private IDoctorService doctorService;
    private final IRetreatService retreatService;

    public BookingService(BookingDao bookingDao, ModelMapperService modelMapperService, IPatientService patientService, IHospitalService hospitalService, IHotelService hotelService, IDoctorService doctorService, IRetreatService retreatService) {
        this.bookingDao = bookingDao;
        this.modelMapperService = modelMapperService;
        this.patientService = patientService;
        this.hospitalService = hospitalService;
        this.hotelService = hotelService;
        this.doctorService = doctorService;
        this.retreatService = retreatService;
    }

    @Autowired
    public void setDoctorService(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }
    
    @Override
    public List<Booking> getAll() {
        return this.bookingDao.findAll();
    }
    
    @Override
    public Booking getById(int id) {
        return this.bookingDao.findById(id).orElse(null);
    }
    
    @Override
    public CreatedBookingResponse add(CreateBookingRequest booking) {
        Patient patient = patientService.getPatientById(booking.getPatient_id());
        Hospital hospital = modelMapperService.forRequest()
                .map(hospitalService.getHospitalById(booking.getHospital_id()), Hospital.class);
        Hotel hotel = hotelService.getById(booking.getHotel_id());
        Doctor doctor = modelMapperService.forRequest()
                .map(doctorService.getDoctorById(booking.getDoctor_id()), Doctor.class);
        GetRetreatByIdResponse retreat = retreatService.getRetreatById(booking.getRetreat_id());


        Booking newBooking = this.modelMapperService.forRequest().map(booking, Booking.class);
        newBooking.setPatient(patient);
        newBooking.setHospital(hospital);
        newBooking.setHotel(hotel);
        newBooking.setDoctor(doctor);
        newBooking.setRetreat(modelMapperService.forRequest().map(retreat, Retreat.class));
        newBooking.setCreateDate(LocalDateTime.now());
        newBooking.setBooking_date(LocalDate.now());
        newBooking.setStatus(BookingStatus.NEW);
        newBooking.setDescription(booking.getDescription());
        return modelMapperService.forResponse().map(bookingDao.save(newBooking), CreatedBookingResponse.class);
    }
    
    @Override
    public void deleteById(int id) {
        this.bookingDao.deleteById(id);
    }
    
    @Override
    public Booking update(Booking booking) {
        return this.bookingDao.save(booking);
    }
}
