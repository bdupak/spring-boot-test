package com.example.api.controller;

import com.example.database.dto.ProductDto;
import com.example.database.service.ProductService;
import com.example.exception.NotFoundException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/{id}")
  @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
  public ResponseEntity<ProductDto> retrieveProductById(@PathVariable("id") final Long productId)
      throws NotFoundException {
    return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
  }

  @PostMapping()
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto product)
      throws NotFoundException {
    return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
  }

  @GetMapping()
  @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
  public ResponseEntity<List<ProductDto>> retrieveProducts() {
    return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
  }

  @PutMapping()
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<ProductDto> updateProduct(@RequestBody final ProductDto product)
      throws NotFoundException {
    return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") final Long productId)
      throws NotFoundException {
    return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.NO_CONTENT);
  }
}
