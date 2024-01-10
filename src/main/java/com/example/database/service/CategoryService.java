package com.example.database.service;

import com.example.database.model.Category;
import com.example.database.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public Category saveCategory(Category category) {
    return categoryRepository.save(category);
  }

  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }
}
