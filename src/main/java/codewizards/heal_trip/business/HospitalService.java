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
    public Hospital registerHospital(Hospital hospital) {
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
}