package codewizards.heal_trip.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.business.*;
import codewizards.heal_trip.entities.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private IAddressService addressService;
    @Autowired
    public AddressController(IAddressService addressService) {
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
    
    @GetMapping("/getById")
    public Address getById(@RequestParam int id){
        return this.addressService.getById(id);
    }
    
    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam int id){
        this.addressService.deleteById(id);
    }
    
    @PutMapping("/update")
    public Address update(@RequestBody Address address){
        return this.addressService.update(address);
    }
}
