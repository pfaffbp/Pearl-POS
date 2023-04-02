package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository repository){this.transactionRepository = repository;}

    public TransactionRecord generateTransaction(List<Product> product, List<Integer> itemsPurchased){
        List<String> productIDS = new ArrayList<>();
        double totalSales = 0;
        Integer quantity = 0;

        for(int i = 0; i < product.size(); i++){
            productIDS.add(product.get(i).getProductID());
            totalSales += itemsPurchased.get(i) * product.get(i).getPrice();
            quantity += itemsPurchased.get(i);

        }
        Transaction transactionGenerator = new Transaction();

        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(transactionGenerator.getTransactionID());
        generatedTransaction.setProductID(productIDS);
        generatedTransaction.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        generatedTransaction.setQuantity(quantity);
        generatedTransaction.setCustomerID("TestCustomer");
        generatedTransaction.setTotalSale(totalSales);
        generatedTransaction.setAmountPurchasedPerProduct(itemsPurchased);

        transactionRepository.save(generatedTransaction);
        return generatedTransaction;
    }
//todo michael look at this shit
/*    public TransactionRecord generateTransation(Map<Product, Integer> productsPurchased){
        List<String> productIDS = new ArrayList<>();
        double totalSales = 0;
        Integer quantity = 0;

        for (Map.Entry<Product, Integer> productIntegerEntry : productsPurchased.entrySet()){
            productIDS.add(productIntegerEntry.getKey().getProductID());
            totalSales += productIntegerEntry.getValue() * productIntegerEntry.getKey().getPrice();
            quantity += productIntegerEntry.getValue();
        }

        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(UUID.randomUUID().toString());
        generatedTransaction.setProductID(productIDS);
        generatedTransaction.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        generatedTransaction.setQuantity(quantity);
        generatedTransaction.setCustomerID("TestCustomer");
        generatedTransaction.setTotalSale(totalSales);
        generatedTransaction.setAmountPurchasedPerProduct(productsPurchased.values().stream().collect(Collectors.toList()));

        transactionRepository.save(generatedTransaction);
        return generatedTransaction;
    }*/

    public Transaction findTransactionByDate(String date){
        Optional<TransactionRecord> record = transactionRepository.findById(date);

        if(record.isPresent()){
            TransactionRecord transactionRecord = record.get();

            return new Transaction(
                    transactionRecord.getDate(),
                    transactionRecord.getCustomerID(),
                    transactionRecord.getProductID(),
                    transactionRecord.getQuantity(),
                    transactionRecord.getTotalSale(),
                    transactionRecord.getTransactionID(),
                    transactionRecord.getAmountPurchasedPerProduct());
        } else {
            return null;
        }

    }


//    public int getQuantityFromCombinations(List<String> combos){
//        int totalQuantity = 0;
//        for(String combo : combos){
//            combo = combo.replaceAll(combo.substring(0, 36), "");
//            combo = combo.replaceAll(combo.substring(combo.indexOf("x"), combo.length()), "");
//            totalQuantity += Integer.parseInt(combo);
//        }
//        return totalQuantity;
//    }
//
//    public double getTotalSalesFromCombinations(List<String> combos){
//        HashMap<Integer, Double> priceWithQuantityMap = new HashMap<>();
//        int quantity = 0;
//        double price = 0;
//        double totalSales = 0;
//
//        for(String combo : combos){
//            quantity = Integer.parseInt(combo.substring(36, combo.lastIndexOf("x")));
//            price = Double.parseDouble(combo.substring(combo.lastIndexOf("x") + 1, combo.length()));
//            priceWithQuantityMap.put(quantity, price);
//
//        }
//
//        for(Map.Entry<Integer, Double> combo : priceWithQuantityMap.entrySet()){
//            totalSales += combo.getKey() * combo.getValue();
//        }
//        return totalSales;
//    }
//
//    public List<String> getProductIDSFromCombinations(List<String> combos){
//        List<String> productIDS = new ArrayList<>();
//
//        for(String combo : combos){
//            productIDS.add(combo.substring(0, 36));
//        }
//        return productIDS;
//    }
}
