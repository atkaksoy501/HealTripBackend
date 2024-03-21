package codewizards.heal_trip.business.concretes;

import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.AddAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.UpdateAddressRequest;
import codewizards.heal_trip.business.DTOs.responses.AddedAddressResponse;
import codewizards.heal_trip.business.DTOs.responses.GotAddressResponse;
import codewizards.heal_trip.business.DTOs.responses.UpdatedAddressResponse;
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
    public List<GotAddressResponse> getAll() {
        List<Address> addresses = this.addressDao.findAll();
        List<GotAddressResponse> gotAddressResponseList = new ArrayList<>();
        for (Address address : addresses) {
            GotAddressResponse gotAddressResponse = this.modelMapperService.forResponse().map(address, GotAddressResponse.class);
            gotAddressResponseList.add(gotAddressResponse);
        }
        return gotAddressResponseList;
    }
    
    @Override
    public GotAddressResponse getById(int id) {
        Address address = this.addressDao.findById(id).orElse(null);
        return this.modelMapperService.forResponse().map(address, GotAddressResponse.class);
    }
    
    @Override
    public AddedAddressResponse add(AddAddressRequest address) {
        Address newAddress = this.modelMapperService.forRequest().map(address, Address.class);
        newAddress.setCreateDate(LocalDateTime.now());
        Address savedAddress = this.addressDao.save(newAddress);
        return this.modelMapperService.forResponse().map(savedAddress, AddedAddressResponse.class);
    }
    
    @Override
    public void deleteById(int id) {
        this.addressDao.deleteById(id);
    }
    
    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest address){
        //get current address
        int id = address.getId();
        Address currentAddress = this.addressDao.findById(id).orElse(null);
        if(currentAddress == null){
            throw new IllegalArgumentException("Address not found");
        }
        //update address
        Address updatedAddress = this.modelMapperService.forResponse().map(address, Address.class);
        updatedAddress.setUpdateDate(LocalDateTime.now());
        updatedAddress.setId(id);
        Address savedAddress = this.addressDao.save(updatedAddress);
        savedAddress.setUpdateDate(updatedAddress.getUpdateDate());
        return this.modelMapperService.forResponse().map(savedAddress, UpdatedAddressResponse.class);
    }
}
