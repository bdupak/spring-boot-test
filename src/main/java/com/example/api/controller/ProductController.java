package com.example.api.controller;

import com.example.database.dto.ProductDto;
import com.example.database.model.Product;
import com.example.database.service.ProductService;
import com.example.exception.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/product/{id}")
  public ResponseEntity<ProductDto> retrieveProductById(@PathVariable("id") final Long productId)
      throws NotFoundException {
    return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
  }

  @PostMapping("/product")
  public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto product)
      throws NotFoundException {
    return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
  }

  @GetMapping("/products") //TODO should be product
  public ResponseEntity<List<ProductDto>> retrieveProducts() {
    return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
  }

  @PutMapping("/product")
  public ResponseEntity<ProductDto> updateProduct(@RequestBody final ProductDto product)
      throws NotFoundException {
    return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
  }

  @DeleteMapping("/product/{id}")
  public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") final Long productId)
      throws NotFoundException {
    return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.NO_CONTENT);
  }
}
