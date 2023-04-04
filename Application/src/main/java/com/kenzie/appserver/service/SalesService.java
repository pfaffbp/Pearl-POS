package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class SalesService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public SalesService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Map<String, Object>> getMonthlySales() {
        Iterable<TransactionRecord> records = transactionRepository.findAll();
        Map<String, List<Double>> monthlySales = new TreeMap<>();
        for (TransactionRecord record : records) {
            LocalDateTime date = LocalDateTime.parse(record.getDate());
            String month = String.format("%d-%02d", date.getYear(), date.getMonthValue());
            if (!monthlySales.containsKey(month)) {
                monthlySales.put(month, new ArrayList<>());
            }
            monthlySales.get(month).add(record.getTotalSale());
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : monthlySales.entrySet()) {
            String month = entry.getKey();
            List<Double> sales = entry.getValue();
            double totalSales = 0;
            for (double sale : sales) {
                totalSales += sale;
            }
            double averageSalePrice = totalSales / sales.size();

            Map<String, Object> monthData = new TreeMap<>();
            monthData.put("month", month);
            monthData.put("totalSales", totalSales);
            monthData.put("averageSalePrice", averageSalePrice);
            result.add(monthData);
        }

        return result;
    }

    public List<Map<String, Object>> getDailySales() {
        Iterable<TransactionRecord> records = transactionRepository.findAll();
        Map<String, List<Double>> dailySales = new TreeMap<>();
        for (TransactionRecord record : records) {
            LocalDateTime date = LocalDateTime.parse(record.getDate());
            String day = String.format("%d-%02d-%02d", date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            if (!dailySales.containsKey(day)) {
                dailySales.put(day, new ArrayList<>());
            }
            dailySales.get(day).add(record.getTotalSale());
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : dailySales.entrySet()) {
            String day = entry.getKey();
            List<Double> sales = entry.getValue();
            double totalSales = 0;
            for (double sale : sales) {
                totalSales += sale;
            }
            double averageSalePrice = totalSales / sales.size();

            Map<String, Object> dayData = new TreeMap<>();
            dayData.put("day", day);
            dayData.put("totalSales", totalSales);
            dayData.put("averageSalePrice", averageSalePrice);
            result.add(dayData);
        }

        return result;
    }


    public List<Map<String, Object>> getHourlySales() {
        Iterable<TransactionRecord> records = transactionRepository.findAll();
        Map<String, List<Double>> hourlySales = new TreeMap<>();
        for (TransactionRecord record : records) {
            LocalDateTime date = LocalDateTime.parse(record.getDate());
            String hour = String.format("%d-%02d-%02d %02d:00", date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour());
            if (!hourlySales.containsKey(hour)) {
                hourlySales.put(hour, new ArrayList<>());
            }
            hourlySales.get(hour).add(record.getTotalSale());
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : hourlySales.entrySet()) {
            String hour = entry.getKey();
            List<Double> sales = entry.getValue();
            double totalSales = 0;
            for (double sale : sales) {
                totalSales += sale;
            }
            double averageSalePrice = totalSales / sales.size();

            Map<String, Object> hourData = new TreeMap<>();
            hourData.put("hour", hour);
            hourData.put("totalSales", totalSales);
            hourData.put("averageSalePrice", averageSalePrice);
            result.add(hourData);
        }

        return result;
    }

}
