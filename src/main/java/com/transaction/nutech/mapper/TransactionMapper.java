package com.transaction.nutech.mapper;

import com.transaction.nutech.model.entity.TransactionEntity;
import com.transaction.nutech.model.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponse map(TransactionEntity entity) {
        return new TransactionResponse(entity.getTransactionType(), entity.getInvoiceNumber(), entity.getDescription(), entity.getTotalAmount(), entity.getCreatedOn());
    }
}
