package com.example.assembler;

import com.example.database.dto.ProductDto;
import com.example.database.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  @Autowired
  private ObjectMapper mapper;

  public ProductDto convertToDto(final Product product) {
    return mapper.convertValue(product, ProductDto.class);
  }

  public List<ProductDto> convertToDto(final List<Product> products) {
    return products.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public Product convertDtoToModel(final ProductDto productDto) {
    return mapper.convertValue(productDto, Product.class);
  }
}
