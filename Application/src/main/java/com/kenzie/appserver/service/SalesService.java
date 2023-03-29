package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.SalesRepository;
import com.kenzie.appserver.repositories.model.SalesRecord;
import com.kenzie.appserver.service.model.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Sales addSales(Sales sales) {
        SalesRecord addNewSale = salesRecordHelperMethod(sales);
        salesRepository.save(addNewSale);
        return sales;
    }

    public Sales findByID(String id) {
        Sales salesRecord = salesRepository.findById(id).orElse(null);
        Sales sales = new Sales();
        sales.setId(salesRecord.getId());
        sales.setName(salesRecord.getName());
        sales.setPrice(salesRecord.getPrice());
        sales.setQuantity(salesRecord.getQuantity());

        return sales;
    }

    public Sales updateSales(String id, Sales sales) {
        if (salesRepository.existsById(id)) {
            SalesRecord salesRecord = salesRecordHelperMethod(sales);
            salesRepository.save(salesRecord);
        }
        return sales;
    }

    public void deleteSale(String saleID) {
        salesRepository.deleteById(saleID);
    }

    public SalesRecord salesRecordHelperMethod(Sales sales) {
        SalesRecord createNewSalesRecord = new SalesRecord();
        createNewSalesRecord.setID(sales.getId());
        createNewSalesRecord.setProductName(sales.getName());
        createNewSalesRecord.setPrice(sales.getPrice());
        createNewSalesRecord.setQuantity(sales.getQuantity());


        return createNewSalesRecord;
    }

    public Sales salesHelperMethod(SalesRecord sales) {
       Sales createNewSale = new Sales();
        createNewSale.setId(sales.getID());
        createNewSale.setName(sales.getProductName());
        createNewSale.setPrice(sales.getPrice());
        createNewSale.setQuantity(sales.getQuantity());
        createNewSale.setDate(sales.getDate());

        return createNewSale;
    }

    public Sales createSales(Sales salesRecord) {
        // Create a new Sales object using the SalesRecord data
        Sales sales = new Sales();
        sales.setName(salesRecord.getName());
        sales.setQuantity(salesRecord.getQuantity());
        sales.setPrice(salesRecord.getPrice());
        sales.setDate(String.valueOf(LocalDate.parse(salesRecord.getDate())));

        // Save the new Sales object using the SalesRepository
        salesRepository.save(sales);

        return sales;
    }

    public List<Sales> getAllSales() {
        // Retrieve all Sales objects using the SalesRepository
        Iterable<Sales> salesIterable = salesRepository.findAll();

        // Convert the Iterable to a List and return it
        List<Sales> salesList = new ArrayList<>();
        salesIterable.forEach(salesList::add);

        return salesList;
    }

    public Sales getSalesById(String id) {
        // Retrieve the Sales object with the given ID using the SalesRepository
        Optional<Sales> optionalSales = salesRepository.findById(id);

        // If the Sales object was found, return it; otherwise, return null
        return optionalSales.orElse(null);
    }

    public Sales deleteSalesById(String id) throws Exception {

            // Retrieve the Sales object with the given ID using the SalesRepository
            Optional<Sales> optionalSales = salesRepository.findById(id);

            // If the Sales object was found, delete it and return it; otherwise, throw an exception
            if (optionalSales.isPresent()) {
                Sales sales = optionalSales.get();
                salesRepository.delete(sales);
                return sales;
            } else {
                throw new Exception("Sales not found");
            }
    }
}


