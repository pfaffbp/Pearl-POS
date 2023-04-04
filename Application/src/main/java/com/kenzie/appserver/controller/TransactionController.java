package com.kenzie.appserver.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.kenzie.appserver.controller.model.TransactionModels.TransactionResponse;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.TransactionService;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService){this.transactionService = transactionService;}

    @GetMapping("/{transactionID}")
    public ResponseEntity<TransactionResponse> getTransactionByTransactionID(
            @PathVariable("transactionID") String transactionID){

        Transaction transaction = transactionService.findTransactionID(transactionID);


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
        transactionResponse.setAmountPurchasedPerProduct(transaction.getAmountPurchasedPerProduct());

        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping("/customer/{customerID}")
    public ResponseEntity<List<TransactionResponse>> getTransactionByCustomerID(@PathVariable("customerID") String customerID){

        //Returns A paginated transactionList for a specific customer
        PaginatedQueryList<TransactionRecord> transactions = transactionService.transactionByCustomerID(customerID);
        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        transactions.forEach(transaction -> transactionResponseList.add(transactionRecordToResponseEntity(transaction)));

        return ResponseEntity.ok(transactionResponseList);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<TransactionResponse>> getTransactionByDate(@PathVariable("date") String date){

        //Returns A paginated transactionList for a specific customer
        List<TransactionRecord> transactions = transactionService.transactionByDate(date);
        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        transactions.forEach(transaction -> transactionResponseList.add(transactionRecordToResponseEntity(transaction)));

        return ResponseEntity.ok(transactionResponseList);
    }


    public TransactionResponse transactionToResponseEntity(Transaction transaction){
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setQuantity(transaction.getQuantity());
        transactionResponse.setTransactionID(transaction.getTransactionID());
        transactionResponse.setProductID(transaction.getProductID());
        transactionResponse.setCustomerID(transaction.getCustomerID());
        transactionResponse.setAmountPurchasedPerProduct(transaction.getAmountPurchasedPerProduct());
        transactionResponse.setTotalSale(transaction.getTotalSale());

        return transactionResponse;
    }

    public TransactionResponse transactionRecordToResponseEntity(TransactionRecord transaction){
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setQuantity(transaction.getQuantity());
        transactionResponse.setTransactionID(transaction.getTransactionID());
        transactionResponse.setProductID(transaction.getProductID());
        transactionResponse.setCustomerID(transaction.getCustomerID());
        transactionResponse.setAmountPurchasedPerProduct(transaction.getAmountPurchasedPerProduct());
        transactionResponse.setTotalSale(transaction.getTotalSale());

        return transactionResponse;
    }

}
