package com.example.utils;

import com.example.database.model.Category;
import java.util.Objects;

public class CategoryPathExtractor {
  private static final String DELIMITER = "/";

  public static String extractFullCategoryPath(final Category category) {
    if(Objects.isNull(category) || Objects.isNull(category.getName())) {
      return null;
    }
    StringBuilder fullCategoryPath = new StringBuilder(category.getName());
    Category ancestor = category.getFatherCategory();

    while (Objects.nonNull(ancestor)) {
      fullCategoryPath.append(DELIMITER);
      fullCategoryPath.append(ancestor.getName());
      ancestor = ancestor.getFatherCategory();
    }
    return fullCategoryPath.toString();
  }
}
