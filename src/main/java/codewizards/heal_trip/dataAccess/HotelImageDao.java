package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelImageDao extends JpaRepository<HotelImage, Integer> {
    HotelImage findByHotelHotelId(int hotel_image_id);
}
