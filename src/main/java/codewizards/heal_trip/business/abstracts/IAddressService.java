package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.requests.address.CreateAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.address.UpdateAddressRequest;
import codewizards.heal_trip.entities.Address;

import java.util.*;

public interface IAddressService {
    List<Address> getAll();
    
    Address getById(int id);
    
    Address add(CreateAddressRequest address);
    
    void deleteById(int id);
    
    Address update(UpdateAddressRequest address, int id);
}
