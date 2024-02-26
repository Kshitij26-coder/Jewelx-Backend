package in.jewelx.jewelxbackend.controller;

import in.jewelx.jewelxbackend.dto.feedback.FeedbackDto;
import in.jewelx.jewelxbackend.service.impl.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackDto feedbackDto) {
    	return ResponseEntity.ok(feedbackService.submitFeedback(feedbackDto));
    	
    }
    // Other endpoints for managing feedback
}

 