package codewizards.heal_trip.business.abstracts;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.AddAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.UpdateAddressRequest;
import codewizards.heal_trip.business.DTOs.responses.AddedAddressResponse;
import codewizards.heal_trip.business.DTOs.responses.GotAddressResponse;
import codewizards.heal_trip.business.DTOs.responses.UpdatedAddressResponse;
import codewizards.heal_trip.entities.Address;

public interface IAddressService {
    List<GotAddressResponse> getAll();
    
    GotAddressResponse getById(int id);
    
    AddedAddressResponse add(AddAddressRequest address);
    
    void deleteById(int id);
    
    UpdatedAddressResponse update(UpdateAddressRequest address);
}
