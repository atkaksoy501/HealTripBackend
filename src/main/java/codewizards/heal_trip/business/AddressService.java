package codewizards.heal_trip.business;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.*;
import codewizards.heal_trip.entities.Address;

@Service
public class AddressService implements IAddressService {
    private AddressDao addressDao;
    
    @Autowired
    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    
    @Override
    public List<Address> getAll() {
        return this.addressDao.findAll();
    }
    
    @Override
    public Optional<Address> getById(int id) {
        return this.addressDao.findById(id);
    }
    
    @Override
    public Address add(Address address) {
        return this.addressDao.save(address);
    }
    
    @Override
    public void deleteById(int id) {
        this.addressDao.deleteById(id);
    }
    
    @Override
    public Address update(Address address){
        return this.addressDao.save(address);
    }
}
