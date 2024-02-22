package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.HospitalOrganizer;
import codewizards.heal_trip.entities.Hotel;

import java.util.List;

public interface IHospitalOrganizerService {
    List<HospitalOrganizer> getAll();
    List<HospitalOrganizer> getAll(int pageNo, int pageSize);
    void add(HospitalOrganizer hospitalOrganizer);
    HospitalOrganizer getById(int id);
    void deleteById(int id);
}
