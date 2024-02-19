package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.RetreatService;
import codewizards.heal_trip.entities.Retreat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/retreat")
public class RetreatController {

    private RetreatService retreatService;

    @Autowired
    public RetreatController(RetreatService retreatService) {
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
}
