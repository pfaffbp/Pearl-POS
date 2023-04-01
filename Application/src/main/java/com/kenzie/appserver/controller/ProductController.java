package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ProductModels.ProductCreateRequest;
import com.kenzie.appserver.controller.model.ProductModels.ProductPurchaseRequest;
import com.kenzie.appserver.controller.model.ProductModels.ProductResponse;
import com.kenzie.appserver.service.ProductService;
import com.kenzie.appserver.service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addNewProduct(@RequestBody ProductCreateRequest createRequest){
        Product product = productHelper(createRequest);
        productService.addProduct(product);

        ProductResponse productResponse = productResponseHelper(product);

        return ResponseEntity.ok(productResponse);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<Product> products = productService.getAllProducts();

        List<ProductResponse> responses = products.stream().map(product -> productResponseHelper(product)).collect(Collectors.toList());
        return ResponseEntity.ok(responses);

    }

    @GetMapping("/{productID}")
    public ResponseEntity<ProductResponse> searchProductById(@PathVariable("productID") String productID){
        Product product = productService.findByProductID(productID);

        if(product == null){
            return ResponseEntity.notFound().build();
        }

        ProductResponse productResponse = productResponseHelper(product);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity deleteProductByID(@PathVariable("productID") String productID){
        try{
            productService.deleteProduct(productID);
        } catch(IllegalArgumentException e){
            ResponseEntity.status(204).body(e);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductCreateRequest productCreateRequest){
        Product product = productHelper(productCreateRequest);
        productService.updateProduct(product);

        ProductResponse productResponse = productResponseHelper(product);

        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{productID}")
    public ResponseEntity<List<ProductResponse>> productsPurchased(@PathVariable("productID") List<String> productID,
                                                            @RequestBody ProductPurchaseRequest productCreateRequest){

        ListIterator<String> listIterator = productID.listIterator();
        List<ProductResponse> productResponses = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        Product product = null;

        while(listIterator.hasNext()) {
            product = productService.findByProductID(listIterator.next());
            if (product != null) {
                productList.add(product);
            }
        }
        List<Integer> itemsPurchased = new ArrayList<>();

        for(int i = 0; i < productCreateRequest.getQuantity().size(); i++){
            itemsPurchased.add(productCreateRequest.getQuantity().get(i));
        }

        List<Product> updateProduct = productService.buyProducts(productList, itemsPurchased);
        for(int i = 0; i < productList.size(); i++){
             productResponses.add(productResponseHelper(updateProduct.get(i)));
        }
        return ResponseEntity.ok(productResponses);
    }

    public Product productHelper(ProductCreateRequest product){
        Product addProduct = new Product();
        addProduct.setProductName(product.getProductName());
        addProduct.setDescription(product.getDescription());
        addProduct.setPrice(product.getPrice());
        addProduct.setQuantity(product.getQuantity());
        addProduct.setCategory(product.getCategory());

        return addProduct;
    }

    public ProductResponse productResponseHelper(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setCategory(product.getCategory());
        productResponse.setProductName(product.getProductName());
        productResponse.setDescription(product.getDescription());
        productResponse.setProductID(product.getProductID());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());

        return productResponse;
    }

}
