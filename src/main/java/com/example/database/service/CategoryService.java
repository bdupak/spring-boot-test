package com.example.database.service;

import com.example.assembler.CategoryMapper;
import com.example.database.dto.CategoryDto;
import com.example.database.model.Category;
import com.example.database.repository.CategoryRepository;
import com.example.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository repository;

  @Autowired
  private CategoryMapper mapper;

  public CategoryDto getCategoryById(final Long categoryId) throws NotFoundException {
    final Optional<Category> categoryFromDb = repository.findById(categoryId);
    if (categoryFromDb.isEmpty()) {
      throw new NotFoundException("Category not found with id = " + categoryId);
    }
    return mapper.convertToDto(categoryFromDb.get());
  }

  public Category saveCategory(final CategoryDto category) {
    return repository.save(mapper.convertFromDtoToModel(category));
  }

  public List<CategoryDto> getCategories() {
    return mapper.convertToDto(repository.findAll());
  }

  public Category updateCategory(final CategoryDto category) throws NotFoundException {
    final Optional<Category> categoryFromDb = repository.findById(category.getId());
    if (categoryFromDb.isEmpty()) {
      throw new NotFoundException("Category not found with id = " + category.getId());
    }
    categoryFromDb.get().setName(category.getName());
    categoryFromDb.get().setFatherCategory(category.getFatherCategory());
    categoryFromDb.get().setIsDeleted(category.getIsDeleted());

    return repository.save(categoryFromDb.get());
  }

  public Category deleteCategory(final Long categoryId) throws NotFoundException {
    final Optional<Category> categoryFromDb = repository.findById(categoryId);
    if (categoryFromDb.isEmpty()) {
      throw new NotFoundException("Category not found with id = " + categoryId);
    }
    categoryFromDb.get().setIsDeleted(true);

    return repository.save(categoryFromDb.get());
  }
}
