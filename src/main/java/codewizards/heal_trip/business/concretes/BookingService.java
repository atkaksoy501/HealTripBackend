package codewizards.heal_trip.business.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.responses.booking.CreatedBookingResponse;
import codewizards.heal_trip.business.abstracts.*;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.entities.*;
import codewizards.heal_trip.entities.enums.BookingStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.*;

@Service
@AllArgsConstructor
public class BookingService implements IBookingService {
    private BookingDao bookingDao;
    private ModelMapperService modelMapperService;
    private IPatientService patientService;
    private IHospitalService hospitalService;
    private IHotelService hotelService;
    private IDoctorService doctorService;
    private IRetreatService retreatService;
    private IFeedbackService feedbackService;

    
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
        Retreat retreat = retreatService.getRetreatById(booking.getRetreat_id());


        Booking newBooking = this.modelMapperService.forRequest().map(booking, Booking.class);
        newBooking.setPatient(patient);
        newBooking.setHospital(hospital);
        newBooking.setHotel(hotel);
        newBooking.setDoctor(doctor);
        newBooking.setRetreat(retreat);
        newBooking.setCreateDate(LocalDateTime.now());
        newBooking.setBooking_date(LocalDate.now());
        newBooking.setStatus(BookingStatus.NEW);
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
