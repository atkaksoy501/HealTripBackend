package codewizards.heal_trip.business;

import java.util.*;
import codewizards.heal_trip.entities.Address;

public interface IAddressService {
    List<Address> getAll();
    
    Optional<Address> getById(int id);
    
    Address add(Address address);
    
    void deleteById(int id);
    
    Address update(Address address);
}
