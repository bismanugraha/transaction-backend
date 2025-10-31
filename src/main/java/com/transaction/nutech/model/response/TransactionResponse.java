package com.transaction.nutech.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {
    @JsonProperty("transaction_type")
    String transactionType;
    @JsonProperty("invoice_number")
    String invoiceNumber;
    String description;
    @JsonProperty("total_amount")
    Long totalAmount;
    @JsonProperty("created_on")
    Date createdOn;
}
