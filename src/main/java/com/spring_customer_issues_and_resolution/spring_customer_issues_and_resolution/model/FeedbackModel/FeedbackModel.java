package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.FeedbackModel;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.TicketModel.TicketModel;

@Document(collection = "feedback")
public class FeedbackModel {
    @Id
    private String feedbackId;
    private String customerNames;
    private String customerPhone;
    private String customerEmail;
    private String message;
    private String channel;
    private StatusEnum status;
    private CategoryEnum category;
    private PriorityEnum priority;
    private LocalDateTime createdAt;
    @DBRef
    private TicketModel ticket;

    public FeedbackModel() {
    }

    public FeedbackModel(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public FeedbackModel(String feedbackId, String customerNames, String customerPhone, String customerEmail,
            String message, String channel, StatusEnum status, CategoryEnum category, PriorityEnum priority,
            LocalDateTime createdAt, TicketModel ticket) {
        this.feedbackId = feedbackId;
        this.customerNames = customerNames;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.message = message;
        this.channel = channel;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.createdAt = createdAt;
        this.ticket = ticket;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getCustomerNames() {
        return customerNames;
    }

    public void setCustomerNames(String customerNames) {
        this.customerNames = customerNames;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TicketModel getTicket() {
        return ticket;
    }

    public void setTicket(TicketModel ticket) {
        this.ticket = ticket;
    }

}