package com.eshop.api.repository;

import com.eshop.api.model.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

  @Query("{'category._id' : ?0}")
  List<Product> getProductByCategoryId(String categoryId);
}
