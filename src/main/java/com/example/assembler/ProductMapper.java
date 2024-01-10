package com.example.assembler;

import com.example.database.dto.ProductDto;
import com.example.database.model.Category;
import com.example.database.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  public static final String DELIMITER = "/";
  @Autowired
  private ObjectMapper mapper;

  public ProductDto convertToDto(final Product product) {
    ProductDto productDto = mapper.convertValue(product, ProductDto.class);
    productDto.setCategoryPath(extractFullCategoryPath(product.getCategory()));
    return productDto;
  }

  public List<ProductDto> convertToDto(final List<Product> products) {
    return products.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public Product convertDtoToModel(final ProductDto productDto) {
    return mapper.convertValue(productDto, Product.class);
  }

  private String extractFullCategoryPath(final Category category) {
    StringBuilder fullCategoryPath = new StringBuilder(category.getName());
    Category ancestor = category.getFatherCategory();

    while (Objects.nonNull(ancestor)) {
      fullCategoryPath.append(DELIMITER);
      fullCategoryPath.append(ancestor.getName());
      ancestor = ancestor.getFatherCategory();
    }
    return fullCategoryPath.toString();
  }
}
