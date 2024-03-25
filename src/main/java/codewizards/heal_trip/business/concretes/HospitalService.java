package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.DTOs.responses.GotHospitalsByDepartmentIdResponse;
import codewizards.heal_trip.business.abstracts.IHospitalService;
import codewizards.heal_trip.dataAccess.HospitalDao;
import codewizards.heal_trip.dataAccess.HospitalDepartmentDao;
import codewizards.heal_trip.entities.Hospital;
import codewizards.heal_trip.entities.HospitalDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HospitalService implements IHospitalService {
    private HospitalDao hospitalDao;
    private HospitalDepartmentDao hospitalDepartmentDao;
    @Autowired
    public HospitalService(HospitalDao hospitalDao, HospitalDepartmentDao hospitalDepartmentDao) {
        this.hospitalDao = hospitalDao;
        this.hospitalDepartmentDao = hospitalDepartmentDao;
    }

    @Override
    public Hospital getHospitalById(int hospital_id) {
        return hospitalDao.findById(hospital_id).orElse(null);
    }

    @Override
    public Hospital registerHospital(Hospital hospital) {
        hospital.setCreateDate(LocalDateTime.now());
        return hospitalDao.save(hospital);
    }

    @Override
    public boolean deleteHospital(int hospital_id) {
        Hospital dbHospital = hospitalDao.findById(hospital_id).orElse(null);
        if (dbHospital != null) {
            dbHospital.setActive(false);
            hospitalDao.save(dbHospital);
            return false;
        }
        return true;
    }

    @Override
    public Hospital updateHospital(Hospital hospital) {
        return hospitalDao.save(hospital);
    }

    //get all
    public List<Hospital> getAllHospitals() {
        return hospitalDao.findAll();
    }

    // get all by departmentId
    public List<GotHospitalsByDepartmentIdResponse> getAllHospitalsByDepartmentId(int departmentId) {
        List<HospitalDepartment> hospitalDepartments = hospitalDepartmentDao.getAllByDepartmentId(departmentId);
        return hospitalDepartments.stream().map(HospitalDepartment::getHospital).toList().stream().map(hospital -> {
            GotHospitalsByDepartmentIdResponse response = new GotHospitalsByDepartmentIdResponse();
            response.setId(hospital.getId());
            response.setHospitalName(hospital.getHospitalName());
            response.setHospitalImages(hospital.getHospitalImages());
            return response;
        }).toList();
    }
}