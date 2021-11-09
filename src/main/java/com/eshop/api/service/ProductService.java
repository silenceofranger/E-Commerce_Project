package com.eshop.api.service;

import com.eshop.api.model.Product;
import com.eshop.api.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public List<Product> getAllProducts(){
    return productRepository.findAll();
  }
  public void addProduct(Product product){
    productRepository.save(product);
  }
  public void removeProductById(String id){
    productRepository.deleteById(id);
  }
  public Optional<Product> getProductById(String id){
    return productRepository.findById(id);
  }

  public List<Product> getAllProductsByCategoryId(String categoryId){
    return productRepository.getProductByCategoryId(categoryId);
  }


}
