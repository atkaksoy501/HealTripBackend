package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.entities.Hotel;
import codewizards.heal_trip.entities.HotelOrganizer;
import java.util.List;

public interface IHotelOrganizerService {
    List<HotelOrganizer> getAll();
    List<HotelOrganizer> getAll(int pageNo, int pageSize);
    Integer add(HotelOrganizer hotelOrganizer);
    HotelOrganizer getById(int id);
    void deleteById(int id);
    void update(HotelOrganizer hotelOrganizer);
}
