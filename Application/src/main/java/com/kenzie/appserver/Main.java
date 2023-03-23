package com.kenzie.appserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.controller.ProductController;
import com.kenzie.appserver.controller.model.ProductCreateRequest;
import com.kenzie.appserver.repositories.ProductRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.service.ProductService;
import com.kenzie.appserver.service.model.Product;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import java.net.URI;
import java.net.http.HttpRequest;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.Scanner;


public class Main {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        boolean turnOffSwitch = true;

        while(turnOffSwitch){
            Scanner optionChooser = new Scanner(System.in);
            System.out.println("Post Request: 1  Put Request: 2  Get Request: 3  Delete Request: 4  " +
                    "Stop Application : 5");
            int optionChoosen = optionChooser.nextInt();

            requestRunner(optionChoosen);

            if(optionChoosen == 5){
                turnOffSwitch = false;
            }
        }

    }

    public static String makePOSTRequest(String url, String requestBody){
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 201 || statusCode == 202) {
                return httpResponse.body();
            } else {
                return String.format("Post request failed: %d status code received", statusCode);
            }
        } catch (InterruptedException | IOException e) {
            return e.getMessage();
        }

        }

    public static String makePutRequest(String url, String requestBody){
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 201 || statusCode == 202) {
                return httpResponse.body();
            } else {
                return String.format("Put request failed: %d status code received", statusCode);
            }
        } catch (InterruptedException | IOException e) {
            return e.getMessage();
        }

    }

    public static String makeGETRequest(String url, String ProductID){
        HttpClient client = HttpClient.newHttpClient();
       String productSearch =  url + ProductID;
        URI uri = URI.create(productSearch);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                // String.format is fun! Worth a Google if you're interested
                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public static ProductCreateRequest createProductRequest(String id, String productName, Double price,
                                                            Integer quantity, String category, String description){

        ProductCreateRequest productCreateRequest = new ProductCreateRequest();
        productCreateRequest.setDescription(description);
        productCreateRequest.setQuantity(quantity);
        productCreateRequest.setProductName(productName);
        productCreateRequest.setPrice(price);
        productCreateRequest.setCategory(category);
        productCreateRequest.setProductID(id);

        return productCreateRequest;
    }

    public static String makeDeleteRequest(String url, String ProductID){
        HttpClient client = HttpClient.newHttpClient();
        String productSearch =  url + ProductID;
        URI uri = URI.create(productSearch);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .DELETE()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                // String.format is fun! Worth a Google if you're interested
                return String.format("Delete request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public static void requestRunner(int option) throws JsonProcessingException {

        if(option == 1){
            Scanner scanner = new Scanner(System.in);
            System.out.println("ProductID");
            String id = scanner.nextLine();

            System.out.println("ProductName");
            String name = scanner.nextLine();

            System.out.println("Price");
            double price = scanner.nextDouble();

            System.out.println("quantity");
            int qty = scanner.nextInt();

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Category");
            String category = scanner2.nextLine();

            System.out.println("Description");
            String description = scanner2.nextLine();


            String productRequest = objectMapper.writeValueAsString(createProductRequest(id,name,price,qty,category,description));

            makePOSTRequest("http://localhost:5001/products", productRequest);
        }
        else if(option == 2){
            Scanner scanner = new Scanner(System.in);
            System.out.println("ProductID");
            String id = scanner.nextLine();

            System.out.println("ProductName");
            String name = scanner.nextLine();

            System.out.println("Price");
            double price = scanner.nextDouble();

            System.out.println("quantity");
            int qty = scanner.nextInt();

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Category");
            String category = scanner2.nextLine();

            System.out.println("Description");
            String description = scanner2.nextLine();


            String productRequest = objectMapper.writeValueAsString(createProductRequest(id,name,price,qty,category,description));
            makePutRequest("http://localhost:5001/products",productRequest);
        }
        else if(option == 3){
            Scanner findProduct = new Scanner(System.in);
            System.out.println("What is the Product ID");
            String productID = findProduct.nextLine();

            String productCaught = makeGETRequest("http://localhost:5001/products/", productID);
            System.out.println(productCaught);
        }
        else if(option == 4){
            Scanner findProduct = new Scanner(System.in);
            System.out.println("What is the Product ID");
            String productID = findProduct.nextLine();

           String productDeleted =  makeDeleteRequest("http://localhost:5001/products/", productID);
           Product product =  objectMapper.readValue(productDeleted, Product.class);
            System.out.println(product + " Was deleted");
        }
    }

}
