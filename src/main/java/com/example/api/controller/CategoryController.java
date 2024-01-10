package com.example.api.controller;

import com.example.database.model.Category;
import com.example.database.service.CategoryService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
  private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
  @Autowired
  private CategoryService categoryService;

  @PostMapping("/category")
  public ResponseEntity<Category> saveCategory(@RequestBody final Category category) {
    LOGGER.info("category: {}", category);
    return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
  }

  @GetMapping("/categories")
  public ResponseEntity<List<Category>> retrieveCategory() {
    return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
  }
}
