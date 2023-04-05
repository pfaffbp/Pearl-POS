package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.TransactionModels.TransactionResponse;
import com.kenzie.appserver.service.TransactionService;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{date}")
    public ResponseEntity<TransactionResponse> getTransactionByDate(@PathVariable("date") String date) {
        Transaction transaction = transactionService.findTransactionByDate(date);

        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionID(transaction.getTransactionID());
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setQuantity(transaction.getQuantity());
        transactionResponse.setProductID(transaction.getProductID());
        transactionResponse.setCustomerID(transaction.getCustomerID());
        transactionResponse.setTotalSale(transaction.getTotalSale());

        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        List<TransactionResponse> responses = transactions.stream().map(this::transactionResponseHelper).collect(Collectors.toList());
        return ResponseEntity.ok(responses);

    }

    public TransactionResponse transactionResponseHelper(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setCustomerID(transaction.getCustomerID());
        transactionResponse.setProductID(transaction.getProductID());
        transactionResponse.setQuantity(transaction.getQuantity());
        transactionResponse.setTotalSale(transaction.getTotalSale());
        transactionResponse.setTransactionID(transaction.getTransactionID());


        return transactionResponse;
    }

    @GetMapping("/report")
    public ResponseEntity<List<Transaction>> generateReport() {
        List<Transaction> report = transactionService.getAllTransactions();
        return ResponseEntity.ok(report);
    }
}
