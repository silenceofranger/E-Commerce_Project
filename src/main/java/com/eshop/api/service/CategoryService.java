package com.eshop.api.service;

import com.eshop.api.model.Category;
import com.eshop.api.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired private CategoryRepository categoryRepository;

  public void saveCategory(Category category){
      categoryRepository.save(category);
  }

  public List<Category> getAllCategory(){
    return categoryRepository.findAll();
  }

  public void removeCategoryById(String id){
    categoryRepository.deleteById(id);
  }

  public Optional<Category> fetchCategoryById(String id) {
    return categoryRepository.findById(id);
  }

}
