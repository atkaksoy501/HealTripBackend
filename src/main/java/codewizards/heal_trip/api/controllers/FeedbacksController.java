package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IFeedbackService;
import codewizards.heal_trip.entities.Feedback;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Feedback Management", description = "Feedback Management APIs")
@RestController
@RequestMapping(value="/feedback")
@CrossOrigin
@AllArgsConstructor
public class FeedbacksController {

    private final IFeedbackService feedbackService;

    @Operation(summary = "Get Feedback By ID")
    @GetMapping(value="/get/{feedback_id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int feedback_id) {
        Feedback feedback = feedbackService.getFeedbackById(feedback_id);
        if (feedback == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @Operation(summary = "Add new Feedback")
    @PostMapping(value = "/add")
    public ResponseEntity<Feedback> registerFeedback(@Valid @RequestBody Feedback feedback) {
        return new ResponseEntity<>(feedbackService.addFeedback(feedback), HttpStatus.OK);
    }

    @Operation(summary = "Delete Feedback by ID")
    @DeleteMapping(value = "/delete/{feedback_id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable int feedback_id) {
        feedbackService.deleteFeedback(feedback_id);
        return new ResponseEntity<>("Feedback with id " + feedback_id + " has been deleted", HttpStatus.OK);
    }

    @Operation(summary = "Update Feedback by ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<Feedback> updateFeedback(@Valid @RequestBody Feedback newFeedback, @PathVariable int id){
        return new ResponseEntity<>(feedbackService.updateFeedback(newFeedback), HttpStatus.OK);
    }
}