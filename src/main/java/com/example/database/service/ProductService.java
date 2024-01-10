package com.example.database.service;

import com.example.assembler.ProductMapper;
import com.example.database.dto.ProductDto;
import com.example.database.model.Product;
import com.example.database.repository.ProductRepository;
import com.example.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  @Autowired
  private ProductMapper mapper;

  @Transactional
  public ProductDto getProductById(final Long productId) throws NotFoundException {
    final Optional<Product> productFromDb = repository.findById(productId);
    if (productFromDb.isEmpty()) {
      throw new NotFoundException("Product not found with id = " + productId);
    }
    return mapper.convertToDto(productFromDb.get());
  }

  @Transactional
  public Product saveProduct(final ProductDto product) {
    return repository.save(mapper.convertDtoToModel(product));
  }

  @Transactional
  public List<ProductDto> getProducts() {
    return mapper.convertToDto(repository.findAll());
  }

  @Transactional
  public Product updateProduct(final ProductDto product) throws NotFoundException {
    final Optional<Product> productFromDb = repository.findById(product.getId());
    if (productFromDb.isEmpty()) {
      throw new NotFoundException("Product not found with id = " + product.getId());
    }
    productFromDb.get().setName(product.getName());
    productFromDb.get().setOverview(product.getOverview());
    productFromDb.get().setPrice(product.getPrice());
    productFromDb.get().setCurrency(product.getCurrency());
    productFromDb.get().setWeight(product.getWeight());
    productFromDb.get().setImageUrl(product.getImageUrl());
    productFromDb.get().setCategory(product.getCategory());
    productFromDb.get().setIsDeleted(product.getIsDeleted());

    return repository.save(productFromDb.get());
  }

  @Transactional
  public Product deleteProduct(final Long productId) throws NotFoundException {
    final Optional<Product> productFromDb = repository.findById(productId);
    if (productFromDb.isEmpty()) {
      throw new NotFoundException("Product not found with id = " + productId);
    }
    productFromDb.get().setIsDeleted(true);

    return repository.save(productFromDb.get());
  }
}