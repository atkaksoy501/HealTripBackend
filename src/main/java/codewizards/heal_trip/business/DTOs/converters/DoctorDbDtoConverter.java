package codewizards.heal_trip.business.DTOs.converters;

import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.DTOs.requests.doctor.UpdateDoctorRequest;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTO;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTOWithHospital;
import codewizards.heal_trip.business.DTOs.responses.doctor.UpdatedDoctorResponse;
import codewizards.heal_trip.business.DTOs.responses.doctor.HospitalForDoctorResponse;
import codewizards.heal_trip.business.abstracts.IBookingService;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.business.abstracts.IHospitalService;
import codewizards.heal_trip.core.converter.Base64ToByteConverter;
import codewizards.heal_trip.core.converter.ByteToBase64Converter;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.entities.Booking;
import codewizards.heal_trip.entities.Doctor;
import codewizards.heal_trip.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorDbDtoConverter {
    private final ModelMapperService modelMapperService;
    private final IHospitalService hospitalService;
    private IDepartmentService departmentService;
    private IBookingService bookingService;

    public DoctorDbDtoConverter(ModelMapperService modelMapperService, IHospitalService hospitalService) {
        this.modelMapperService = modelMapperService;
        this.hospitalService = hospitalService;
    }

    @Autowired
    @Lazy
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    @Lazy
    public void setBookingService(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Doctor toDbObj(CreateDoctorRequest dto) {
        Doctor newDoctor = new Doctor();
        newDoctor.setExperience_year(dto.getExperience_year());
        newDoctor.setDoctorName(dto.getDoctorName());
        newDoctor.setDoctorImage(Base64ToByteConverter.convert(dto.getDoctorImage()));
        newDoctor.setHospital(modelMapperService.forRequest()
                .map(hospitalService.getHospitalById(dto.getHospital_id()), Hospital.class));
        newDoctor.setDepartment(departmentService.getById(dto.getDepartment_id()));
        newDoctor.setDescription(dto.getDescription());
        newDoctor.setCreateDate(LocalDateTime.now());
        newDoctor.setActive(true);
        return newDoctor;
    }

    public Doctor toDbObj(Doctor doctor, UpdateDoctorRequest dto) {
        modelMapperService.forUpdate().map(dto, doctor);
        doctor.setDoctorImage(Base64ToByteConverter.convert(dto.getDoctorImage()));
        doctor.setHospital(modelMapperService.forRequest()
                .map(hospitalService.getHospitalById(dto.getHospital_id()), Hospital.class));
        doctor.setDepartment(departmentService.getById(dto.getDepartment_id()));
        List<Booking> bookings = new ArrayList<>();
        for (int bookingId : dto.getBooking_ids()) {
            bookings.add(bookingService.getById(bookingId));
        }
        doctor.setBookings(bookings);
        doctor.setDescription(dto.getDescription());
        doctor.setUpdateDate(LocalDateTime.now());
        return doctor;
    }

    public DoctorDTOWithHospital toDto(Doctor dbObj) {
        DoctorDTOWithHospital dto = new DoctorDTOWithHospital();
        dto.setId(dbObj.getId());
        dto.setExperience_year(dbObj.getExperience_year());
        dto.setDoctorName(dbObj.getDoctorName());
        dto.setDoctorImage(ByteToBase64Converter.convert(dbObj.getDoctorImage()));
        dto.setHospital(modelMapperService.forResponse().map(dbObj.getHospital(), HospitalForDoctorResponse.class));
        dto.setDepartment(modelMapperService.forResponse().map(dbObj.getDepartment(), DepartmentDTO.class));
        dto.setDescription(dbObj.getDescription());
        return dto;
    }

    public DoctorDTO toDoctorDto(Doctor dbObj) {
        DoctorDTO dto = new DoctorDTO();
        dto.setId(dbObj.getId());
        dto.setExperience_year(dbObj.getExperience_year());
        dto.setDoctorName(dbObj.getDoctorName());
        dto.setDoctorImage(ByteToBase64Converter.convert(dbObj.getDoctorImage()));
        dto.setDepartment(modelMapperService.forResponse().map(dbObj.getDepartment(), DepartmentDTO.class));
        return dto;
    }

    public UpdatedDoctorResponse toUpdateDto(Doctor dbObj) {
        UpdatedDoctorResponse dto = new UpdatedDoctorResponse();
        dto.setId(dbObj.getId());
        dto.setExperience_year(dbObj.getExperience_year());
        dto.setDoctorName(dbObj.getDoctorName());
        dto.setDoctorImage(ByteToBase64Converter.convert(dbObj.getDoctorImage()));
        dto.setActive(dbObj.isActive());
        dto.setHospital(modelMapperService.forResponse().map(dbObj.getHospital(), HospitalForDoctorResponse.class));
        dto.setDepartment(modelMapperService.forResponse().map(dbObj.getDepartment(), DepartmentDTO.class));
        dto.setDescription(dbObj.getDescription());
        dto.setUpdateDate(dbObj.getUpdateDate());
        List<Integer> bookingIds = new ArrayList<>();
        for (Booking booking : dbObj.getBookings()) {
            bookingIds.add(booking.getId());
        }
        dto.setBooking_ids(bookingIds);
        return dto;
    }
}
