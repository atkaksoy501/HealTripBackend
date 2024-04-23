package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.address.CreateAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.address.UpdateAddressRequest;
import codewizards.heal_trip.business.abstracts.IAddressService;
import codewizards.heal_trip.entities.Address;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@CrossOrigin
@AllArgsConstructor
public class AddressesController {
    private final IAddressService addressService;
    
    @GetMapping("/getAll")
    public List<Address> getAll() {
        return this.addressService.getAll();
    }
    
    @PostMapping("/add")
    public Address add(@Valid @RequestBody CreateAddressRequest address) {
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
    public Address update(@Valid @RequestBody UpdateAddressRequest address, @PathVariable int id){
        return this.addressService.update(address, id);
    }
}
