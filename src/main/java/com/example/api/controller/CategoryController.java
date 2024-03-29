package com.example.api.controller;

import com.example.database.dto.CategoryDto;
import com.example.database.service.CategoryService;
import com.example.exception.NotFoundException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
  private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
  @Autowired
  private CategoryService categoryService;

  @GetMapping("/{id}")
  @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
  public ResponseEntity<CategoryDto> retrieveCategoryById(@PathVariable("id") final Long categoryId)
      throws NotFoundException {
    return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
  }

  @PostMapping()
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<CategoryDto> saveCategory(@RequestBody final CategoryDto category) {
    LOGGER.info("category: {}", category);
    return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
  }

  @GetMapping()
  @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
  public ResponseEntity<List<CategoryDto>> retrieveCategories() {
    return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
  }

  @PutMapping()
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<CategoryDto> updateCategory(@RequestBody final CategoryDto category)
      throws NotFoundException {
    return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @RolesAllowed("ROLE_ADMIN")
  public ResponseEntity<CategoryDto> deleteCategory(@PathVariable("id") final Long categoryId)
      throws NotFoundException {
    return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.NO_CONTENT);
  }
}
