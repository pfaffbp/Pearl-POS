package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final List<Transaction> transactions;

    @Autowired
    public TransactionService(TransactionRepository repository, List<Transaction> transactions, ProductService productService, UserService userService){this.transactionRepository = repository;
        this.transactions = transactions;
    }

    public TransactionRecord generateTransaction(Product product, int itemsPurchased){
        Transaction transactionGenerator = new Transaction();// default constructor creates transaction ID

        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(transactionGenerator.getTransactionID());
        generatedTransaction.setQuantity(itemsPurchased);
        generatedTransaction.setDate(LocalDateTime.now().toString());
        generatedTransaction.setProductID(product.getProductID());
        generatedTransaction.setCustomerID(transactionGenerator.getCustomerID());
        generatedTransaction.setTotalSale(product.getPrice() * itemsPurchased);

        transactionRepository.save(generatedTransaction);
        return generatedTransaction;
    }

    public TransactionRecord generateTransactionReport(String customerID){
        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setCustomerID(customerID);
        generatedTransaction.setDate(LocalDateTime.now().toString());
        generatedTransaction.setTransactionID("Report");
        return generatedTransaction;
    }

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

        public List<Transaction> generateReport() {
            List<Transaction> report = new ArrayList<>();
            for (TransactionRecord transactionRecord : transactionRepository.findAll()) {
                report.add(new Transaction(
                        transactionRecord.getDate(),
                        transactionRecord.getCustomerID(),
                        transactionRecord.getProductID(),
                        transactionRecord.getQuantity(),
                        transactionRecord.getTotalSale(),
                        transactionRecord.getTransactionID()));
            }
            return report;
        }
    }

