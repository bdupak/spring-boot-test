package com.example.assembler;

import com.example.database.dto.ProductDto;
import com.example.database.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  @Autowired
  private ObjectMapper mapper;

  public ProductDto convertToDto(final Product product) {
    ProductDto productDto = mapper.convertValue(product, ProductDto.class);
    return productDto;
  }
}
