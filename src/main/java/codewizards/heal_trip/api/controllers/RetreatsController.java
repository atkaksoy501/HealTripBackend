package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.requests.retreat.AddRetreatRequest;
import codewizards.heal_trip.business.DTOs.requests.retreat.UpdateRetreatRequest;
import codewizards.heal_trip.business.DTOs.responses.retreat.*;
import codewizards.heal_trip.business.abstracts.IRetreatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Retreat Management", description = "Retreat Management APIs")
@RestController
@RequestMapping(value = "/retreat")
@CrossOrigin
@AllArgsConstructor
public class RetreatsController {

    private final IRetreatService retreatService;

    @Operation(summary = "Get Retreat by ID")
    @GetMapping(value = "/get/{retreat_id}")
    public ResponseEntity<GetRetreatByIdResponse> getRetreatById(@PathVariable int retreat_id) {
        GetRetreatByIdResponse retreat = retreatService.getRetreatById(retreat_id);
        if (retreat == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(retreat, HttpStatus.OK);
    }

    @Operation(summary = "Delete Retreat by ID")
    @DeleteMapping(value = "/delete/{retreat_id}")
    public ResponseEntity<String> deleteRetreat(@PathVariable int retreat_id) {
        boolean isDeleted = retreatService.deleteRetreat(retreat_id);
        if (isDeleted)
            return new ResponseEntity<>("Retreat with id = " + retreat_id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update Retreat by ID")
    @PutMapping(value = "/update/{retreat_id}")
    public ResponseEntity<UpdatedRetreatResponse> updateRetreat(@Valid @RequestBody UpdateRetreatRequest retreat, @PathVariable int retreat_id) {
        return new ResponseEntity<>(retreatService.updateRetreat(retreat, retreat_id), HttpStatus.OK);
    }

    @Operation(summary = "Add new Retreat")
    @PostMapping(value = "/add")
    public ResponseEntity<AddedRetreatResponse> addRetreat(@Valid @RequestBody AddRetreatRequest retreat) {
        return new ResponseEntity<>(retreatService.addRetreat(retreat), HttpStatus.OK);
    }

    @Operation(summary = "Get all Retreats")
    @GetMapping(value = "/getAll")
    public ResponseEntity<Iterable<GetAllRetreatsResponse>> getAllRetreats() {
        return new ResponseEntity<>(retreatService.getAllRetreats(), HttpStatus.OK);
    }

    @Operation(summary = "Get Retreats by Department ID")
    @GetMapping(value = "/getByDepartmentId/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GotRetreatByDepartmentIdResponse> getRetreatsByDepartmentId(@PathVariable int departmentId) {
        return retreatService.getRetreatsByDepartmentId(departmentId);
    }

    @Operation(summary = "Get Retreats by Hospital ID")
    @GetMapping(value = "/getByHospitalId/{hospitalId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetRetreatByHospitalIdResponse> getRetreatsByHospitalId(@PathVariable int hospitalId) {
        return retreatService.getRetreatsByHospitalId(hospitalId);
    }
}
