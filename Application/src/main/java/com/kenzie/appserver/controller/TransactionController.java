package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.TransactionModels.TransactionResponse;
import com.kenzie.appserver.service.TransactionService;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService, List<Transaction> transactions){this.transactionService = transactionService;
        this.transactions = transactions;
    }

    private final List<Transaction> transactions;


    @GetMapping("/{date}")
    public ResponseEntity<TransactionResponse> getTransactionByDate(@PathVariable("date") String date){
        Transaction transaction = transactionService.findTransactionByDate(date);

        if(transaction == null){
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

    @GetMapping("/api/transactions")
    public List<Transaction> getAllTransactions() {
        return transactions;
    }
}

//    @PostMapping()
//    public ResponseEntity<ProductResponse> createTransaction(@RequestBody TransactionCreateRequest transactionCreateRequest){
//        Transaction transaction = new Transaction();
//        transactionService.generateTransaction()
//    }

