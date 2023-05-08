package com.misiak.transactions.web;

import com.misiak.transactions.model.AccountSummary;
import com.misiak.transactions.model.Transaction;
import com.misiak.transactions.service.TransactionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionProcessor transactionProcessor;

    @PostMapping(value = "/transactions/report", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<AccountSummary>> processTransactions(@RequestBody List<Transaction> transactions) {
        return ok(transactionProcessor.processTransactions(transactions));
    }
}
