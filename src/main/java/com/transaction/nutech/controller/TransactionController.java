package com.transaction.nutech.controller;

import com.transaction.nutech.model.request.TransactionRequest;
import com.transaction.nutech.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.transaction.nutech.constant.Paths.GET_ALL_TRANSACTION_HISTORY;
import static com.transaction.nutech.constant.Paths.TRANSACTION;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(
            path = TRANSACTION,
            method = RequestMethod.POST
    )
    public ResponseEntity<?> transaction(
            @RequestHeader(name = "Authorization") String authorization,
            @RequestBody TransactionRequest transactionRequest
    ) {
        return transactionService.transaction(authorization, transactionRequest);
    }

    @RequestMapping(
            path = GET_ALL_TRANSACTION_HISTORY,
            method = RequestMethod.GET
    )
    public ResponseEntity<?> transaction(
            @RequestHeader(name = "Authorization") String authorization,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        return transactionService.getAllTransactionHistory(authorization, offset, limit);
    }
}
