package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Hotel;
import java.util.List;

public interface IHotelService {
    List<Hotel> getAll();
    List<Hotel> getAllSorted();
    List<Hotel> getAll(int pageNo, int pageSize);
    void add(Hotel hotel);
    Hotel getById(int id);
    void deleteById(int id);
}
