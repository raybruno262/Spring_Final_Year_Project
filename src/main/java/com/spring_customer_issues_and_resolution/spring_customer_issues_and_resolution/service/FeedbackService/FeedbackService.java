package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.FeedbackService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.dto.NLPClassificationResponse;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.FeedbackModel.CategoryEnum;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.FeedbackModel.FeedbackModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.FeedbackModel.PriorityEnum;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.FeedbackModel.StatusEnum;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.FeedbackRepository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private NLPService nlpService;

    public ResponseEntity<String> createFeedback(FeedbackModel feedback) {
        try {
            // Validate required fields
            if (feedback.getCustomerNames() == null || feedback.getMessage() == null) {
                return ResponseEntity.ok("Status 3000");
            }

            // Classify the feedback message using NLP
            NLPClassificationResponse classification = nlpService.classifyFeedback(feedback.getMessage());

            // Set category and priority from NLP results
            feedback.setCategory(CategoryEnum.valueOf(classification.getCategory()));
            feedback.setPriority(PriorityEnum.valueOf(classification.getPriority()));

            // Set timestamps
            feedback.setCreatedAt(LocalDateTime.now());

            // Set default status if not provided
            if (feedback.getStatus() == null) {
                feedback.setStatus(StatusEnum.NEW);
            }

            feedbackRepository.save(feedback);

            return ResponseEntity.ok("Status 1000");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }
    }

    public ResponseEntity<String> updateFeedback(String feedbackId, FeedbackModel updatedFeedback) {
        try {
            Optional<FeedbackModel> existingFeedback = feedbackRepository.findById(feedbackId);
            if (existingFeedback.isEmpty()) {
                return ResponseEntity.ok("Status 3000");
            }

            FeedbackModel existing = existingFeedback.get();

            // If message changed, reclassify
            if (!existing.getMessage().equals(updatedFeedback.getMessage())) {

                NLPClassificationResponse classification = nlpService.classifyFeedback(updatedFeedback.getMessage());
                updatedFeedback.setCategory(CategoryEnum.valueOf(classification.getCategory()));
                updatedFeedback.setPriority(PriorityEnum.valueOf(classification.getPriority()));
            } else {
                // Keep existing classification
                updatedFeedback.setCategory(existing.getCategory());
                updatedFeedback.setPriority(existing.getPriority());
            }

            updatedFeedback.setFeedbackId(feedbackId);
            updatedFeedback.setCreatedAt(existing.getCreatedAt());

            feedbackRepository.save(updatedFeedback);

            return ResponseEntity.ok("Status 1000");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }
    }

    public ResponseEntity<?> getFeedbackById(String feedbackId) {
        try {
            Optional<FeedbackModel> feedback = feedbackRepository.findById(feedbackId);
            if (feedback.isPresent()) {
                return ResponseEntity.ok(feedback.get());
            } else {
                return ResponseEntity.ok("Status 3000");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }
    }

    public ResponseEntity<?> getAllFeedback() {
        try {
            List<FeedbackModel> feedbackList = feedbackRepository.findAll();
            return ResponseEntity.ok(feedbackList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }
    }

    public ResponseEntity<String> deleteFeedback(String feedbackId) {
        try {
            Optional<FeedbackModel> existingFeedback = feedbackRepository.findById(feedbackId);
            if (existingFeedback.isEmpty()) {
                return ResponseEntity.ok("Status 3000");
            }

            feedbackRepository.deleteById(feedbackId);
            return ResponseEntity.ok("Status 1000");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }
    }
}