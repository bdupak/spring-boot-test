package com.example.api.controller;

import com.example.database.model.Product;
import com.example.database.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("/product")
  public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
    return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
  }

  @GetMapping("/products")
  public ResponseEntity<List<Product>> retrieveProducts() {
    return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
  }
}
