package com.example.mapper;

import com.example.database.dto.ProductDto;
import com.example.database.model.Product;
import com.example.exception.NotFoundException;
import com.example.utils.CategoryPathExtractor;
import com.example.utils.CurrencyConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private CurrencyConverter currencyConverter;

  public ProductDto convertToDto(final Product product) {
    ProductDto productDto = mapper.convertValue(product, ProductDto.class);
    productDto.setCategoryPath(
        CategoryPathExtractor.extractFullCategoryPath(product.getCategory()));
    return productDto;
  }

  public List<ProductDto> convertToDto(final List<Product> products) {
    return products.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public Product convertDtoToModel(final ProductDto productDto) throws NotFoundException {
    Product product = mapper.convertValue(productDto, Product.class);
    return currencyConverter.convertCurrency(product);
  }
}
