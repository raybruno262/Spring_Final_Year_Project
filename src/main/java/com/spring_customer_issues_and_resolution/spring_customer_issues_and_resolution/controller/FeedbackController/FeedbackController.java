package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.controller.FeedbackController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.FeedbackModel.FeedbackModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.FeedbackService.FeedbackService;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.FeedbackService.NLPService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private NLPService nlpService;

    @PostMapping("/createFeedback")
    public ResponseEntity<String> createFeedback(@RequestBody FeedbackModel feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @GetMapping("/getAllFeedback")
    public ResponseEntity<?> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/getFeedbackById")
    public ResponseEntity<?> getFeedbackById(@RequestParam String feedbackId) {
        return feedbackService.getFeedbackById(feedbackId);
    }

    @PutMapping("/updateFeedback")
    public ResponseEntity<String> updateFeedback(@RequestParam String feedbackId,
            @RequestBody FeedbackModel feedback) {
        return feedbackService.updateFeedback(feedbackId, feedback);
    }

    @DeleteMapping("/deleteFeedback")
    public ResponseEntity<String> deleteFeedback(@RequestParam String feedbackId) {
        return feedbackService.deleteFeedback(feedbackId);
    }

    @GetMapping("/checkNLPHealth")
    public ResponseEntity<String> checkNLPServiceHealth() {
        return nlpService.checkNLPServiceHealth();
    }
}