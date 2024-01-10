package com.example.assembler;

import com.example.database.dto.CategoryDto;
import com.example.database.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
  @Autowired
  private ObjectMapper mapper;

  public CategoryDto convertToDto(final Category category) {
    CategoryDto categoryDto = mapper.convertValue(category, CategoryDto.class);
    return categoryDto;
  }
}
