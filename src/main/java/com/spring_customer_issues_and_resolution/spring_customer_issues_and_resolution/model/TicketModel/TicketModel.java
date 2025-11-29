package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.TicketModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ticket")
public class TicketModel {
    @Id
    private String ticketId;

}
