package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository repository){this.transactionRepository = repository;}

//    public TransactionRecord generateTransaction(Product product, int itemsPurchased){
//        Transaction transactionGenerator = new Transaction();// default constructor creates transaction ID
//
//        TransactionRecord generatedTransaction = new TransactionRecord();
//        generatedTransaction.setTransactionID(transactionGenerator.getTransactionID());
//        generatedTransaction.setQuantity(itemsPurchased);
//        generatedTransaction.setDate(LocalDateTime.now().toString());
//        generatedTransaction.setProductID(product.getProductID());
//        generatedTransaction.setCustomerID("TestCustomer"); //change this later
//        generatedTransaction.setTotalSale(product.getPrice() * itemsPurchased);
//
//        transactionRepository.save(generatedTransaction);
//        return generatedTransaction;
//    }

    public Transaction findTransactionByDate(String date){
        Optional<TransactionRecord> record = transactionRepository.findById(date);

        if(record.isPresent()){
            TransactionRecord transactionRecord = record.get();

            return new Transaction(
                    transactionRecord.getDate(),
                    transactionRecord.getCustomerID(),
                    transactionRecord.getProductID(),
                    transactionRecord.getQuantity(),
                    transactionRecord.getTotalSale(),transactionRecord.getTransactionID());
        } else {
            return null;
        }
    }

    public TransactionRecord generateTransaction(Transaction transaction) {
        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(transaction.getTransactionID());
        generatedTransaction.setQuantity(transaction.getQuantity());
        generatedTransaction.setDate(transaction.getDate());
        generatedTransaction.setProductID(transaction.getProductID());
        generatedTransaction.setCustomerID(transaction.getCustomerID());
        generatedTransaction.setTotalSale(transaction.getTotalSale());

        transactionRepository.save(generatedTransaction);
        return generatedTransaction;
    }

    public void addTransaction(Product product, int itemsPurchased) {
        Transaction transactionGenerator = new Transaction();// default constructor creates transaction ID

        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(transactionGenerator.getTransactionID());
        generatedTransaction.setQuantity(itemsPurchased);
        generatedTransaction.setDate(LocalDateTime.now().toString());
        generatedTransaction.setProductID(product.getProductID());
        generatedTransaction.setCustomerID("TestCustomer"); //change this later
        generatedTransaction.setTotalSale(product.getPrice() * itemsPurchased);

        transactionRepository.save(generatedTransaction);
    }
}
