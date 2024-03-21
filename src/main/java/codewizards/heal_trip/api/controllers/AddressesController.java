package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.AddAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.UpdateAddressRequest;
import codewizards.heal_trip.business.DTOs.responses.AddedAddressResponse;
import codewizards.heal_trip.business.DTOs.responses.GotAddressResponse;
import codewizards.heal_trip.business.DTOs.responses.UpdatedAddressResponse;
import codewizards.heal_trip.business.abstracts.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.entities.*;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressesController {
    private IAddressService addressService;
    @Autowired
    public AddressesController(IAddressService addressService) {
        this.addressService = addressService;
    }
    
    @GetMapping("/getAll")
    public List<GotAddressResponse> getAll() {
        return this.addressService.getAll();
    }
    
    @PostMapping("/add")
    public AddedAddressResponse add(@RequestBody AddAddressRequest address) {
        return this.addressService.add(address);
    }
    
    @GetMapping("/get/{id}")
    public GotAddressResponse getById(@PathVariable int id){
        return this.addressService.getById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        this.addressService.deleteById(id);
    }
    
    @PutMapping("/update")
    public UpdatedAddressResponse update(@RequestBody UpdateAddressRequest address){
        return this.addressService.update(address);
    }
}
