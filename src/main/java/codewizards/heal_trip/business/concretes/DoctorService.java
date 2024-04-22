package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.DTOs.converters.DoctorDbDtoConverter;
import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.DTOs.requests.doctor.UpdateDoctorRequest;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTOWithHospital;
import codewizards.heal_trip.business.DTOs.responses.doctor.UpdatedDoctorResponse;
import codewizards.heal_trip.business.abstracts.IDoctorService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.DoctorDao;
import codewizards.heal_trip.entities.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService implements IDoctorService {
    private DoctorDao doctorDao;
    private DoctorDbDtoConverter doctorDbDtoConverter;
    private ModelMapperService modelMapperService;

    @Override
    public DoctorDTOWithHospital getDoctorById(int doctor_id) {
        Doctor doctor = doctorDao.findById(doctor_id).orElse(null);
        return doctorDbDtoConverter.toDto(doctor);
    }
    @Override
    public DoctorDTOWithHospital registerDoctor(CreateDoctorRequest doctor) {
        Doctor newDoctor = doctorDbDtoConverter.toDbObj(doctor);
        Doctor dbDoctor = doctorDao.save(newDoctor);
        return doctorDbDtoConverter.toDto(dbDoctor);
    }
    @Override
    public boolean deleteDoctor(int doctor_id) {
        Doctor dbDoctor = doctorDao.findById(doctor_id).orElse(null);
        if (dbDoctor != null) {
            dbDoctor.setActive(false);
            doctorDao.save(dbDoctor);
            return false;
        }
        return true;
    }

    @Override
    public UpdatedDoctorResponse updateDoctor(UpdateDoctorRequest doctor, int id) {
        Doctor dbDoctor = doctorDao.findById(id).orElse(null);
        Doctor updatedDoctor = doctorDbDtoConverter.toDbObj(dbDoctor, doctor);
        Doctor savedDoctor = doctorDao.save(updatedDoctor);
        return doctorDbDtoConverter.toUpdateDto(savedDoctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.findAll();
    }
}