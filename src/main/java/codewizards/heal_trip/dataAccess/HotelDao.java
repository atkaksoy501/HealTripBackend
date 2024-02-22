package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDao extends JpaRepository<Hotel,Integer>{
    Hotel findById(int id);
}
