package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.FeedbackService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.dto.NLPClassificationResponse;

@Service
public class NLPService {

    @Value("${nlp.service.url}")
    private String nlpServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public NLPClassificationResponse classifyFeedback(String message) {
        try {
            if (message == null || message.trim().isEmpty()) {
                return getFallbackResponse();
            }

            String url = nlpServiceUrl + "/classify";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text", message);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<NLPClassificationResponse> response = restTemplate.postForEntity(
                    url,
                    request,
                    NLPClassificationResponse.class);

            NLPClassificationResponse result = response.getBody();

            if (result != null) {

                return result;
            } else {

                return getFallbackResponse();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return getFallbackResponse();
        }
    }

    public ResponseEntity<String> checkNLPServiceHealth() {
        try {
            String url = nlpServiceUrl + "/health";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Status 1000"); // Service is healthy
            } else {
                return ResponseEntity.ok("Status 2000"); // Service unhealthy
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999"); // Service unavailable
        }
    }

    private NLPClassificationResponse getFallbackResponse() {
        NLPClassificationResponse fallback = new NLPClassificationResponse();
        fallback.setCategory("GENERAL_INQUIRY");
        fallback.setPriority("LOW");
        fallback.setCategoryConfidence(0.0);
        fallback.setPriorityConfidence(0.0);
        return fallback;
    }
}