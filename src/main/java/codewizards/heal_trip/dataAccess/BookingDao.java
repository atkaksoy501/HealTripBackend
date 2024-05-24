package codewizards.heal_trip.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import codewizards.heal_trip.entities.Booking;

public interface BookingDao extends JpaRepository<Booking, Integer> {
}
