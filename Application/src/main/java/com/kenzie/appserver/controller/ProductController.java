package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ProductModels.ProductCreateRequest;
import com.kenzie.appserver.controller.model.ProductModels.ProductPurchaseRequest;
import com.kenzie.appserver.controller.model.ProductModels.ProductResponse;
import com.kenzie.appserver.service.ProductService;
import com.kenzie.appserver.service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

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
    public ResponseEntity<ProductResponse> productPurchased(@PathVariable("productID") String productID,
                                                            @RequestBody ProductPurchaseRequest productCreateRequest){


        Product product = productService.findByProductID(productID);
        if(product != null){
           Product updateProduct = productService.buyProducts(product, productCreateRequest.getQuantity());
                if(updateProduct == null){
                    return ResponseEntity.badRequest().build();
                }
           ProductResponse response = productResponseHelper(updateProduct);
            return ResponseEntity.ok(response);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    //I need to figure out
//    @PutMapping("/cart")
//    public ResponseEntity<ProductResponse> productPurchased(@RequestBody List<ProductCreateRequest> purchaseRequests){
//
//        ListIterator<ProductCreateRequest> purchaseRequestListIterator = purchaseRequests.listIterator();
//        Map<Product, Integer> productsForPurchaseMap = new HashMap<>();
//
//        while(purchaseRequestListIterator.hasNext()){
//            ProductCreateRequest productBeforeRepository = purchaseRequestListIterator.next();
//            Product product = productService.findByProductID(productBeforeRepository.getProductID());
//
//            if(product != null){
//                productsForPurchaseMap.put(product, productBeforeRepository.getQuantity());
//            }
//        }
//        productService.buyMultipleProducts(productsForPurchaseMap);
//
//        return ResponseEntity.ok().build();
//    }

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
