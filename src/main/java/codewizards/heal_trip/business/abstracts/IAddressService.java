package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.entities.Address;

import java.util.*;

public interface IAddressService {
    List<Address> getAll();
    
    Address getById(int id);
    
    Address add(Address address);
    
    void deleteById(int id);
    
    Address update(Address address);
}
