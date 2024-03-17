package codewizards.heal_trip.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.business.*;
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
    public List<Address> getAll() {
        return this.addressService.getAll();
    }
    
    @PostMapping("/add")
    public Address add(@RequestBody Address address) {
        return this.addressService.add(address);
    }
    
    @GetMapping("/get/{id}")
    public Address getById(@PathVariable int id){
        return this.addressService.getById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        this.addressService.deleteById(id);
    }
    
    @PutMapping("/update/{id}")
    public Address update(@RequestBody Address address, @PathVariable int addressId){
        return this.addressService.update(address);
    }
}
