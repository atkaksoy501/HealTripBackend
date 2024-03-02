package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.HospitalOrganizerDao;
import codewizards.heal_trip.entities.Hospital;
import codewizards.heal_trip.entities.HospitalOrganizer;
import codewizards.heal_trip.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HospitalOrganizerService implements IHospitalOrganizerService{

    private HospitalOrganizerDao hospitalOrganizerDao;
    private IHospitalService hospitalService;

    @Autowired
    public HospitalOrganizerService(HospitalOrganizerDao hospitalOrganizerDao, IHospitalService hospitalService) {
        super();
        this.hospitalOrganizerDao = hospitalOrganizerDao;
        this.hospitalService = hospitalService;
    }

    @Override
    public List<HospitalOrganizer> getAll() {
        return this.hospitalOrganizerDao.findAll();
    }

    @Override
    public List<HospitalOrganizer> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        return this.hospitalOrganizerDao.findAll(pageable).getContent();
    }

    @Override
    public void add(HospitalOrganizer hospitalOrganizer) {
        this.hospitalOrganizerDao.save(hospitalOrganizer);
    }

    @Override
    public HospitalOrganizer getById(int id) {
        return this.hospitalOrganizerDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        this.hospitalOrganizerDao.deleteById(id);
    }

    @Override
    public void update(HospitalOrganizer hospitalOrganizer) {
        this.hospitalOrganizerDao.save(hospitalOrganizer);
    }

    @Override
    public HospitalOrganizer createHospitalOrganizerWithHospital(int hospitalId) {
        HospitalOrganizer hospitalOrganizer = new HospitalOrganizer();
        hospitalOrganizer.setActive(true);
        hospitalOrganizer.setEmail("atkaksoy501@hotmail.com");
        hospitalOrganizer.setFirst_name("Atakan");
        hospitalOrganizer.setLast_name("Aksoy");
        hospitalOrganizer.setPhone_number("1234567890");
        hospitalOrganizer.setUser_password("123456");
        hospitalOrganizer.setUser_role("hospital_organizer");

        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        hospitalOrganizer.setHospital(hospital);
        return hospitalOrganizer;
    }
}
