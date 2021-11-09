package com.eshop.api.dto;

import com.eshop.api.model.Category;
import java.util.UUID;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductDTO {
  private String id = UUID.randomUUID().toString();
  private String name;
  private String categoryId;
  private double price;
  private double weight;
  private String description;
  private String imageName;
}
