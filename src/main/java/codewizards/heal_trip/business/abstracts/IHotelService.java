package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.responses.hotel.GetAllHotelsForAiResponse;
import codewizards.heal_trip.entities.Hotel;
import java.util.List;

public interface IHotelService {
    List<Hotel> getAll();
    List<Hotel> getAllSorted();
    List<Hotel> getAll(int pageNo, int pageSize);
    Hotel add(Hotel hotel);
    Hotel getById(int id);
    void deleteById(int id);
    void update(Hotel hotel);
    List<GetAllHotelsForAiResponse> getAllHotelsForAI();
    Long getHotelCount();
}
