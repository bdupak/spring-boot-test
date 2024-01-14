package com.example.database.service;

import com.example.mapper.CategoryMapper;
import com.example.database.dto.CategoryDto;
import com.example.database.entity.Category;
import com.example.database.repository.CategoryRepository;
import com.example.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository repository;

  @Autowired
  private CategoryMapper mapper;

  @Transactional
  public CategoryDto getCategoryById(final Long categoryId) throws NotFoundException {
    final Optional<Category> categoryFromDb = repository.findById(categoryId);
    if (categoryFromDb.isEmpty()) {
      throw new NotFoundException("Category not found with id = " + categoryId);
    }
    return mapper.convertToDto(categoryFromDb.get());
  }

  @Transactional
  public CategoryDto saveCategory(final CategoryDto category) {
    return mapper.convertToDto(repository.save(mapper.convertFromDtoToModel(category)));
  }

  @Transactional
  public List<CategoryDto> getCategories() {
    return mapper.convertToDto(repository.findAll());
  }

  @Transactional
  public CategoryDto updateCategory(final CategoryDto category) throws NotFoundException {
    final Optional<Category> categoryFromDb = repository.findById(category.getId());
    if (categoryFromDb.isEmpty()) {
      throw new NotFoundException("Category not found with id = " + category.getId());
    }
    final Category updatedCategory = mapper.convertFromDtoToModel(category);

    return mapper.convertToDto(repository.save(updatedCategory));
  }

  @Transactional
  public CategoryDto deleteCategory(final Long categoryId) throws NotFoundException {
    final Optional<Category> categoryFromDb = repository.findById(categoryId);
    if (categoryFromDb.isEmpty()) {
      throw new NotFoundException("Category not found with id = " + categoryId);
    }
    categoryFromDb.get().setIsDeleted(true);

    return mapper.convertToDto(repository.save(categoryFromDb.get()));
  }
}
