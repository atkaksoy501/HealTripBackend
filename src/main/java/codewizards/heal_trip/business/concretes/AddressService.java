package codewizards.heal_trip.business.concretes;

import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.address.CreateAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.address.UpdateAddressRequest;
import codewizards.heal_trip.business.abstracts.IAddressService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.*;
import codewizards.heal_trip.entities.Address;

@Service
public class AddressService implements IAddressService {
    private AddressDao addressDao;
    private ModelMapperService modelMapperService;
    
    @Autowired
    public AddressService(AddressDao addressDao, ModelMapperService modelMapperService) {
        this.addressDao = addressDao;
        this.modelMapperService = modelMapperService;
    }
    
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
        this.addressDao.deleteById(id);
    }
    
    @Override
    public Address update(UpdateAddressRequest address, int id){
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
