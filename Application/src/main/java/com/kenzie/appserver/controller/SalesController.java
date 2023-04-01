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

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

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
