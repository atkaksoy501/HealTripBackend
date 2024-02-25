package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.HotelImage;
import codewizards.heal_trip.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelImageDTO extends JpaRepository<HotelImage, Integer> {
}
