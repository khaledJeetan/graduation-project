package com.project.nextstep.controllers;

import com.project.nextstep.entity.construction.Product;
import com.project.nextstep.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{productId}")
    @CrossOrigin(origins = "http://localhost")
    public Product retrieveProduct(
            @PathVariable Long productId
    ){
        return productService.getProduct(productId);
    }

    @GetMapping("/products")
    public List<Product> retrieveAllProducts(){
        return productService.getAllProducts();
    }



    @PostMapping("/suppliers/{supplierId}/products")
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product,
            @PathVariable Long supplierId
    ){
        Product p = productService.addProduct(product,supplierId);
        return ResponseEntity.ok().body(p);
    }

    @GetMapping("/suppliers/{supplierId}/products")
    public List<Product> getSupplierProducts(
            @PathVariable Long supplierId
    ){
        List<Product> products = productService.getSupplierProducts(supplierId);
        return products;
    }

}
