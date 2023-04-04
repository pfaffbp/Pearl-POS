package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ProductModels.ProductResponse;
import com.kenzie.appserver.controller.model.TransactionModels.TransactionResponse;
import com.kenzie.appserver.service.TransactionService;
import com.kenzie.appserver.service.model.Product;
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
        transactionResponse.setAmountPurchasedPerProduct(transaction.getAmountPurchasedPerProduct());

        return ResponseEntity.ok(transactionResponse);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        List<TransactionResponse> responses = transactions.stream().map(this::transactionResponseHelper).collect(Collectors.toList());
=======
    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(){
        List<Transaction> transactions = transactionService.getAllTransactions();

        List<TransactionResponse> responses = transactions.stream().map(transaction -> transactionResponseHelper(transaction)).collect(Collectors.toList());
>>>>>>> 9fcaff4 (prep to merg 4/3 8:46)
        return ResponseEntity.ok(responses);

    }

<<<<<<< HEAD
    public TransactionResponse transactionResponseHelper(Transaction transaction) {
=======
    public TransactionResponse transactionResponseHelper(Transaction transaction){
>>>>>>> 9fcaff4 (prep to merg 4/3 8:46)
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setCustomerID(transaction.getCustomerID());
        transactionResponse.setProductID(transaction.getProductID());
        transactionResponse.setQuantity(transaction.getQuantity());
        transactionResponse.setTotalSale(transaction.getTotalSale());
        transactionResponse.setTransactionID(transaction.getTransactionID());
<<<<<<< HEAD

=======
        transactionResponse.setAmountPurchasedPerProduct(transaction.getAmountPurchasedPerProduct());
>>>>>>> 9fcaff4 (prep to merg 4/3 8:46)

        return transactionResponse;
    }

<<<<<<< HEAD
    @GetMapping("/report")
    public ResponseEntity<List<Transaction>> generateReport() {
        List<Transaction> report = transactionService.getAllTransactions();
        return ResponseEntity.ok(report);
    }
=======
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)
=======
>>>>>>> 9fcaff4 (prep to merg 4/3 8:46)
}
