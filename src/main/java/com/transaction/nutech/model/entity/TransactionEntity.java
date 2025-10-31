package com.transaction.nutech.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    String transactionType;
    String invoiceNumber;
    String description;
    Long totalAmount;
    Date createdOn;
    String userEmail;
}
