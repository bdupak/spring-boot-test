package com.example.database.service;

import com.example.mapper.ProductMapper;
import com.example.database.dto.ProductDto;
import com.example.database.entity.Product;
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
  public ProductDto saveProduct(final ProductDto product) throws NotFoundException {
    return mapper.convertToDto(repository.save(mapper.convertDtoToModel(product)));
  }

  @Transactional
  public List<ProductDto> getProducts() {
    return mapper.convertToDto(repository.findAll());
  }

  @Transactional
  public ProductDto updateProduct(final ProductDto product) throws NotFoundException {
    final Optional<Product> productFromDb = repository.findById(product.getId());
    if (productFromDb.isEmpty()) {
      throw new NotFoundException("Product not found with id = " + product.getId());
    }
    final Product updatedProduct = mapper.convertDtoToModel(product);

    return mapper.convertToDto(repository.save(updatedProduct));
  }

  @Transactional
  public ProductDto deleteProduct(final Long productId) throws NotFoundException {
    final Optional<Product> productFromDb = repository.findById(productId);
    if (productFromDb.isEmpty()) {
      throw new NotFoundException("Product not found with id = " + productId);
    }
    productFromDb.get().setIsDeleted(true);

    return mapper.convertToDto(repository.save(productFromDb.get()));
  }
}
