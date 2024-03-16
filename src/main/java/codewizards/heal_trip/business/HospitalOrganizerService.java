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

    @Autowired
    public HospitalOrganizerService(HospitalOrganizerDao hospitalOrganizerDao) {
        super();
        this.hospitalOrganizerDao = hospitalOrganizerDao;
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
    public Integer add(HospitalOrganizer hospitalOrganizer) {
        return this.hospitalOrganizerDao.save(hospitalOrganizer).getId();
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
}
