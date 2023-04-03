package com.kenzie.appserver.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class ReportController {

    private final AmazonDynamoDB dynamoDB;

    public ReportController(AmazonDynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        try {
            DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            List<Transaction> transactions = mapper.scan(Transaction.class, scanExpression);

            // Generate report using the transactions
            String report = "Transaction Report: Transaction ID Customer ID Transaction Date Amount";

            // Compress report data into a ZIP file
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);
            ZipEntry entry = new ZipEntry("report.txt");
            zos.putNextEntry(entry);
            zos.write(report.getBytes());
            zos.closeEntry();
            zos.close();

            // Set response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "report.zip");

            return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getReport")
    public ResponseEntity<String> getReport() {
        try {
            // Fetch report data from a database or file
            String reportData = "Report data";
            return new ResponseEntity<>(reportData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
