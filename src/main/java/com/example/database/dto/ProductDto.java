package com.example.database.dto;

import com.example.database.model.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ProductDto {
  private Long id;
  private String name;
  private String overview;
  private BigDecimal price;
  private String currency;
  private Integer weight;
  private String imageUrl;
  private Category category;
  private String categoryPath;
  private Boolean isDeleted;
}
