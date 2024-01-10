package com.example.database.service;

import com.example.database.model.Product;
import com.example.database.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public List<Product> getProducts() {
    return productRepository.findAll();
  }
}
