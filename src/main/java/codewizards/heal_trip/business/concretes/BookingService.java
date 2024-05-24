package codewizards.heal_trip.business.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.responses.booking.CreatedBookingResponse;
import codewizards.heal_trip.business.DTOs.responses.patient.GetPatientResponse;
import codewizards.heal_trip.business.DTOs.responses.retreat.GetRetreatByIdResponse;
import codewizards.heal_trip.business.abstracts.*;
import codewizards.heal_trip.business.messages.EmailMessages;
import codewizards.heal_trip.business.rules.BookingBusinessRules;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.entities.*;
import codewizards.heal_trip.entities.enums.BookingStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.*;

@Service
@AllArgsConstructor
public class BookingService implements IBookingService {
    private final BookingDao bookingDao;
    private final ModelMapperService modelMapperService;
    private final IPatientService patientService;
    private final IHospitalService hospitalService;
    private final IHotelService hotelService;
    private IDoctorService doctorService;
    private final IRetreatService retreatService;
    private final BookingBusinessRules bookingBusinessRules;
    private final IEmailService emailService;

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
        bookingBusinessRules.checkIfBookingExists(id);
        return this.bookingDao.findById(id).orElse(null);
    }
    
    @Override
    public CreatedBookingResponse add(CreateBookingRequest booking) {
        Patient patient = patientService.getPatient(booking.getPatient_id());
        GetRetreatByIdResponse retreat = retreatService.getRetreatById(booking.getRetreat_id());

        Booking newBooking = this.modelMapperService.forRequest().map(booking, Booking.class);
        newBooking.setPatient(patient);

        if (bookingBusinessRules.checkIfBookingsHospitalExists(booking.getHospital_id())) {
            Hospital hospital = modelMapperService.forRequest()
                    .map(hospitalService.getHospitalById(booking.getHospital_id()), Hospital.class);
            newBooking.setHospital(hospital);
        }
        if (bookingBusinessRules.checkIfBookingsHotelExists(booking.getHotel_id())) {
            Hotel hotel = hotelService.getById(booking.getHotel_id());
            newBooking.setHotel(hotel);
        }
        if (bookingBusinessRules.checkIfBookingsDoctorExists(booking.getDoctor_id())) {
            Doctor doctor = modelMapperService.forRequest()
                    .map(doctorService.getDoctorById(booking.getDoctor_id()), Doctor.class);
            newBooking.setDoctor(doctor);
        }

        newBooking.setRetreat(modelMapperService.forRequest().map(retreat, Retreat.class));
        newBooking.setCreateDate(LocalDateTime.now());
        newBooking.setBooking_date(LocalDate.now());
        newBooking.setStatus(BookingStatus.NEW);
        newBooking.setDescription(booking.getDescription());
        Booking dbBooking = bookingDao.save(newBooking);
        try {
            emailService.sendAppointmentEmail(dbBooking);
        } catch (Exception e) {
            throw new BusinessException(EmailMessages.EMAIL_COULD_NOT_BE_SENT);
        }
        return modelMapperService.forRequest().map(dbBooking, CreatedBookingResponse.class);
    }
    
    @Override
    public void deleteById(int id) {
        bookingBusinessRules.checkIfBookingExists(id);
        this.bookingDao.deleteById(id);
    }
    
    @Override
    public Booking update(Booking booking) {
        bookingBusinessRules.checkIfBookingExists(booking.getId());
        return this.bookingDao.save(booking); //todo: implement
    }
}
