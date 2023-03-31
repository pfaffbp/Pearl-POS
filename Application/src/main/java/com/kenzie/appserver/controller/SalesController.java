<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
package com.kenzie.appserver.controller;


import com.kenzie.appserver.service.SalesService;
import com.kenzie.appserver.service.model.Sales;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SalesController {
/*    private final SalesService salesService;

=======
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
>>>>>>> 05a2520 (preptomerge)
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

<<<<<<< HEAD
    @PostMapping
    public Sales createSales(@RequestBody Sales sales) {
        return salesService.addSales(sales);
    }

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sales getSales(@PathVariable String id) {
        return salesService.findByID(id);
    }

    @PutMapping("/{id}")
    public Sales updateSales(@PathVariable String id, @RequestBody Sales sales) {
        return salesService.updateSales(id, sales);
    }*/

}
=======
=======
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)
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
<<<<<<< HEAD
>>>>>>> d6bc43a (preping to merge)

=======
//
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)
=======
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
>>>>>>> 05a2520 (preptomerge)
