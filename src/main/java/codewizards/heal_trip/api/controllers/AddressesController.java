package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.address.CreateAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.address.UpdateAddressRequest;
import codewizards.heal_trip.business.abstracts.IAddressService;
import codewizards.heal_trip.entities.Address;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Address Management", description = "Address Management APIs")
@RestController
@RequestMapping("/address")
@CrossOrigin
@AllArgsConstructor
public class AddressesController {
    private final IAddressService addressService;

    @Operation(summary = "Get all Addresses")
    @GetMapping("/getAll")
    public List<Address> getAll() {
        return this.addressService.getAll();
    }

    @Operation(summary = "Create new Address")
    @PostMapping("/add")
    public Address add(@Valid @RequestBody CreateAddressRequest address) {
        return this.addressService.add(address);
    }

    @Operation(summary = "Get Address by ID")
    @GetMapping("/get/{id}")
    public Address getById(@PathVariable int id){
        return this.addressService.getById(id);
    }

    @Operation(summary = "Delete Address by ID")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        this.addressService.deleteById(id);
    }

    @Operation(summary = "Update Address by ID")
    @PutMapping("/update/{id}")
    public Address update(@Valid @RequestBody UpdateAddressRequest address, @PathVariable int id){
        return this.addressService.update(address, id);
    }
}
