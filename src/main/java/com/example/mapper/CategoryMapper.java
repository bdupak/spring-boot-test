package com.example.mapper;

import com.example.database.dto.CategoryDto;
import com.example.database.entity.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
  @Autowired
  private ObjectMapper mapper;

  public CategoryDto convertToDto(final Category category) {
    return mapper.convertValue(category, CategoryDto.class);
  }

  public List<CategoryDto> convertToDto(final List<Category> categories) {
    return categories.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public Category convertFromDtoToModel(final CategoryDto categoryDto) {
    return mapper.convertValue(categoryDto, Category.class);
  }
}
