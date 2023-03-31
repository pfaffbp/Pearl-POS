package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<Map<String, Object>>> getMonthlySales() {
        List<Map<String, Object>> monthlySalesList = salesService.getMonthlySales();
        return ResponseEntity.ok(monthlySalesList);
    }

    @GetMapping("/daily")
    public ResponseEntity<List<Map<String, Object>>> getDailySales() {
        List<Map<String, Object>> dailySalesList = salesService.getDailySales();
        return ResponseEntity.ok(dailySalesList);
    }

    @GetMapping("/top")
    public ResponseEntity<List<Map<String, Object>>> getTopSellingProducts() {
        List<Map<String, Object>> topSellingProductsList = salesService.getTopSellingProducts();
        return ResponseEntity.ok(topSellingProductsList);
    }

    @GetMapping("/hourly")
    public ResponseEntity<List<Map<String, Object>>> getHourlySales() {
        List<Map<String, Object>> hourlySalesList = salesService.getHourlySales();
        return ResponseEntity.ok(hourlySalesList);
    }


}
