package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.TransactionModels.TransactionResponse;
import com.kenzie.appserver.service.TransactionService;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService){this.transactionService = transactionService;}

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

//    @PostMapping()
//    public ResponseEntity<ProductResponse> createTransaction(@RequestBody TransactionCreateRequest transactionCreateRequest){
//        Transaction transaction = new Transaction();
//        transactionService.generateTransaction()
//    }
}
