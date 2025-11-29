package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.dto;

public class NLPClassificationResponse {
    private String category;
    private String priority;
    private Double categoryConfidence;
    private Double priorityConfidence;

    public NLPClassificationResponse() {
    }

    public NLPClassificationResponse(String category, String priority, Double categoryConfidence,
            Double priorityConfidence) {
        this.category = category;
        this.priority = priority;
        this.categoryConfidence = categoryConfidence;
        this.priorityConfidence = priorityConfidence;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Double getCategoryConfidence() {
        return categoryConfidence;
    }

    public void setCategoryConfidence(Double categoryConfidence) {
        this.categoryConfidence = categoryConfidence;
    }

    public Double getPriorityConfidence() {
        return priorityConfidence;
    }

    public void setPriorityConfidence(Double priorityConfidence) {
        this.priorityConfidence = priorityConfidence;
    }

}
