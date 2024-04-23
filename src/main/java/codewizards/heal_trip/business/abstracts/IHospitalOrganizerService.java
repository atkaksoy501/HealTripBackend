package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.entities.HospitalOrganizer;

import java.util.List;

public interface IHospitalOrganizerService {
    List<HospitalOrganizer> getAll();
    List<HospitalOrganizer> getAll(int pageNo, int pageSize);
    Integer add(HospitalOrganizer hospitalOrganizer);
    HospitalOrganizer getById(int id);
    void deleteById(int id);
    void update(HospitalOrganizer hospitalOrganizer);
}
