package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Hotel;
import codewizards.heal_trip.entities.HotelOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelOrganizerDao extends JpaRepository<HotelOrganizer,Integer> {
}
