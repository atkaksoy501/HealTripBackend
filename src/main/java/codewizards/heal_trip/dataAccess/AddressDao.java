package codewizards.heal_trip.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import codewizards.heal_trip.entities.Address;

public interface AddressDao extends JpaRepository<Address, Integer> {
}
