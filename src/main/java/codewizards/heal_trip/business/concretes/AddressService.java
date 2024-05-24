package codewizards.heal_trip.business.concretes;

import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.address.CreateAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.address.UpdateAddressRequest;
import codewizards.heal_trip.business.abstracts.IAddressService;
import codewizards.heal_trip.business.rules.AddressBusinessRules;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.*;
import codewizards.heal_trip.entities.Address;

@Service
@AllArgsConstructor
public class AddressService implements IAddressService {
    private final AddressDao addressDao;
    private final ModelMapperService modelMapperService;
    private final AddressBusinessRules addressBusinessRules;
    
    @Override
    public List<Address> getAll() {
        List<Address> addresses = this.addressDao.findAll();
        List<Address> gotAddressResponseList = new ArrayList<>();
        for (Address address : addresses) {
            Address gotAddressResponse = this.modelMapperService.forResponse().map(address, Address.class);
            gotAddressResponseList.add(gotAddressResponse);
        }
        return gotAddressResponseList;
    }
    
    @Override
    public Address getById(int id) {
        addressBusinessRules.checkIfAddressExists(id);
        Address address = this.addressDao.findById(id).orElse(null);
        return this.modelMapperService.forResponse().map(address, Address.class);
    }
    
    @Override
    public Address add(CreateAddressRequest address) {
        Address newAddress = this.modelMapperService.forRequest().map(address, Address.class);
        newAddress.setCreateDate(LocalDateTime.now());
        Address savedAddress = this.addressDao.save(newAddress);
        return this.modelMapperService.forResponse().map(savedAddress, Address.class);
    }
    
    @Override
    public void deleteById(int id) {
        addressBusinessRules.checkIfAddressExists(id);
        this.addressDao.deleteById(id);
    }
    
    @Override
    public Address update(UpdateAddressRequest address, int id){
        addressBusinessRules.checkIfAddressExists(id);
        //get current address
        Address currentAddress = this.addressDao.findById(id).orElse(null);
        if(currentAddress == null){
            throw new IllegalArgumentException("Address not found");
        }
        //update address
        Address updatedAddress = this.modelMapperService.forResponse().map(address, Address.class);
        updatedAddress.setUpdateDate(LocalDateTime.now());
        updatedAddress.setCreateDate(currentAddress.getCreateDate());
        updatedAddress.setId(address.getId());
        Address savedAddress = this.addressDao.save(updatedAddress);
        return this.modelMapperService.forResponse().map(savedAddress, Address.class);
    }
}
