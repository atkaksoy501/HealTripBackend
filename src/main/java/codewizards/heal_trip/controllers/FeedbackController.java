package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.FeedbackService;
import codewizards.heal_trip.entities.Doctor;
import codewizards.heal_trip.entities.Feedback;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        super();
        this.feedbackService = feedbackService;
    }

    @GetMapping("/getFeedbackById/{feedback_id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int feedback_id) {
        Feedback feedback = feedbackService.getFeedbackById(feedback_id);
        if (feedback == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Integer> registerUser(@RequestBody Feedback feedback) {
        return new ResponseEntity<>(feedbackService.addFeedback(feedback), HttpStatus.OK);
    }
}
