package com.kenzie.appserver.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final AmazonDynamoDB dynamoDB;

    private final TransactionService transactionService;

    public ReportController(AmazonDynamoDB dynamoDB, TransactionService transactionService) {
        this.dynamoDB = dynamoDB;
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<byte[]> generateReport() {
        try {
            // Fetch report data from a database or file
            List<TransactionRecord> transactionReport = transactionService.getAllTransactions();
            String report = transactionReport.toString();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            ZipEntry zipEntry = new ZipEntry("report.txt");
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(report.getBytes());
            zipOutputStream.closeEntry();
            zipOutputStream.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("report.txt", "report.txt");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<String> getReport() {
        try {
               // Fetch report data from a database or file
                List<TransactionRecord> transactionReport = transactionService.getAllTransactions();
                String report = transactionReport.toString();

                return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
