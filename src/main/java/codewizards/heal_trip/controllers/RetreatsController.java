package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IRetreatService;
import codewizards.heal_trip.entities.Retreat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/retreat")
@CrossOrigin
public class RetreatsController {

    private IRetreatService retreatService;

    @Autowired
    public RetreatsController(IRetreatService retreatService) {
        super();
        this.retreatService = retreatService;
    }

    @GetMapping(value = "/getById/{retreat_id}")
    public ResponseEntity<Retreat> getRetreatById(@PathVariable int retreat_id) {
        Retreat retreat = retreatService.getRetreatById(retreat_id);
        if (retreat == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(retreat, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{retreat_id}")
    public ResponseEntity<String> deleteRetreat(@PathVariable int retreat_id) {
        boolean isDeleted = retreatService.deleteRetreat(retreat_id);
        if (isDeleted)
            return new ResponseEntity<>("Retreat with id = " + retreat_id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{retreat_id}")
    public ResponseEntity<Retreat> updateRetreat(@RequestBody Retreat retreat, @PathVariable int retreat_id) {
        return new ResponseEntity<>(retreatService.updateRetreat(retreat, retreat_id), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> addRetreat(@RequestBody Retreat retreat) {
        return new ResponseEntity<>(retreatService.addRetreat(retreat), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Iterable<Retreat>> getAllRetreats() {
        return new ResponseEntity<>(retreatService.getAllRetreats(), HttpStatus.OK);
    }
}
