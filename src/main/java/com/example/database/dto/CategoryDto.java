package com.example.database.dto;

import com.example.database.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CategoryDto {
  private Long id;
  private String name;
  private Category fatherCategory;
  private Boolean isDeleted;
}
