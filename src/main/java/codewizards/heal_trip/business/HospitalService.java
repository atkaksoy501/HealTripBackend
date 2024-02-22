package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.HospitalDao;
import codewizards.heal_trip.entities.Doctor;
import codewizards.heal_trip.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService implements IHospitalService{
    private HospitalDao hospitalDao;
    @Autowired
    public HospitalService(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @Override
    public Hospital getHospitalById(int hospital_id) {
        return hospitalDao.findById(hospital_id).orElse(null);
    }

    @Override
    public Integer registerHospital(Hospital hospital) {
        Hospital newHospital = new Hospital();
        newHospital.setId(hospital.getId());
        newHospital.setBed_capacity(hospital.getBed_capacity());
        newHospital.setHospitalName(hospital.getHospitalName());
        newHospital.setAddressId(hospital.getAddressId());
        newHospital.setContactPhone(hospital.getContactPhone());
        newHospital = hospitalDao.save(newHospital);
        return newHospital.getId();
    }
}
