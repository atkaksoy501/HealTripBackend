package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.concretes.FeedbackService;
import codewizards.heal_trip.entities.Feedback;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/feedback")
@CrossOrigin
public class FeedbacksController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbacksController(FeedbackService feedbackService) {
        super();
        this.feedbackService = feedbackService;
    }

    @GetMapping(value="/get/{feedback_id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int feedback_id) {
        Feedback feedback = feedbackService.getFeedbackById(feedback_id);
        if (feedback == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
    @PostMapping(value = "/add")
    public ResponseEntity<Feedback> registerFeedback(@RequestBody Feedback feedback) {
        return new ResponseEntity<>(feedbackService.addFeedback(feedback), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{feedback_id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable int feedback_id) {
        feedbackService.deleteFeedback(feedback_id);
        return new ResponseEntity<>("Feedback with id " + feedback_id + " has been deleted", HttpStatus.OK);
    }

    @PutMapping("/update/{feedback_id}")
    public ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback newFeedback, @PathVariable int feedbackId){
        return new ResponseEntity<>(feedbackService.updateFeedback(newFeedback), HttpStatus.OK);
    }
}