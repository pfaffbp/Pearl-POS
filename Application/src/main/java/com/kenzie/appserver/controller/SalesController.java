//package com.kenzie.appserver.controller;
//
//import com.kenzie.appserver.service.SalesService;
//import com.kenzie.appserver.service.model.Sales;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@RestController
//@RequestMapping("/sales")
//public class SalesController {
//
//    private final SalesService salesService;
//
//    public SalesController(SalesService salesService) {
//        this.salesService = salesService;
//    }
//
//    @PostMapping
//    public Sales createSales(@RequestBody Sales sales) {
//        return salesService.createSales(sales);
//    }
//
//    @GetMapping
//    public List<Sales> getAllSales() {
//        return salesService.getAllSales();
//    }
//
//    @GetMapping("/{id}")
//    public Sales getSales(@PathVariable String id) {
//        return salesService.getSalesById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Sales updateSales(@PathVariable String id, @RequestBody Sales sales) {
//        return salesService.updateSales(id, sales);
//    }
//}