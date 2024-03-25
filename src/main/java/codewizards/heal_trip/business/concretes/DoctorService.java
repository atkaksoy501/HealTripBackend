package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.DTOs.responses.DoctorDTOWithHospital;
import codewizards.heal_trip.business.abstracts.IDoctorService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.DoctorDao;
import codewizards.heal_trip.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService implements IDoctorService {
    private DoctorDao doctorDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public DoctorService(DoctorDao doctorDao, ModelMapperService modelMapperService) {
        this.doctorDao = doctorDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DoctorDTOWithHospital getDoctorById(int doctor_id) {
        Doctor doctor = doctorDao.findById(doctor_id).orElse(null);
        return modelMapperService.forResponse().map(doctor, DoctorDTOWithHospital.class);
    }
    @Override
    public Doctor registerDoctor(Doctor doctor) {
        doctor.setCreateDate(LocalDateTime.now());
        return doctorDao.save(doctor);
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
    public Doctor updateDoctor(Doctor doctor) {
        return doctorDao.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.findAll();
    }
}