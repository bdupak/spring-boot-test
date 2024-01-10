package com.example.api.controller;

import com.example.database.dto.CategoryDto;
import com.example.database.model.Category;
import com.example.database.service.CategoryService;
import com.example.exception.NotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
  private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
  @Autowired
  private CategoryService categoryService;

  @GetMapping("/category/{id}")
  public ResponseEntity<CategoryDto> retrieveCategoryById(@PathVariable("id") final Long categoryId)
      throws NotFoundException {
    return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
  }

  @PostMapping("/category")
  public ResponseEntity<Category> saveCategory(@RequestBody final CategoryDto category) {
    LOGGER.info("category: {}", category);
    return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
  }

  @GetMapping("/categories")
  public ResponseEntity<List<CategoryDto>> retrieveCategories() {
    return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
  }

  @PutMapping("/category")
  public ResponseEntity<Category> updateCategory(@RequestBody final CategoryDto category)
      throws NotFoundException {
    return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
  }

  @DeleteMapping("/category/{id}")
  public ResponseEntity<Category> deleteCategory(@PathVariable("id") final Long categoryId)
      throws NotFoundException {
    return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
  }
}
