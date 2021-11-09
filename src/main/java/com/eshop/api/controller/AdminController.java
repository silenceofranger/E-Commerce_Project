package com.eshop.api.controller;

import com.eshop.api.dto.ProductDTO;
import com.eshop.api.model.Category;
import com.eshop.api.model.Product;
import com.eshop.api.service.CategoryService;
import com.eshop.api.service.ProductService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
  @Autowired
  CategoryService categoryService;
  @Autowired
  ProductService productService;

  public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

  @GetMapping("/admin")
  public String adminHome() {
    return "adminHome";
  }

  @GetMapping("/admin/categories")
  public String getCategories(Model model) {
    model.addAttribute("categories", categoryService.getAllCategory());
    return "categories";
  }

  @GetMapping("/admin/categories/add")
  public String addCategories(Model model) {
    model.addAttribute("category", new Category());
    return "categoriesAdd";
  }

  @PostMapping("/admin/categories/add")
  public String saveCategories(@ModelAttribute("category") Category category) {
    categoryService.saveCategory(category);
    return "redirect:/admin/categories";
  }

  @GetMapping("/admin/categories/delete/{id}")
  public String deleteCategories(@PathVariable String id) {
    categoryService.removeCategoryById(id);
    return "redirect:/admin/categories";
  }

  @GetMapping("/admin/categories/update/{id}")
  public String deleteCategories(@PathVariable String id, Model model) {
    Optional<Category> categoryOptional = categoryService.fetchCategoryById(id);
    if(categoryOptional.isPresent()){
      model.addAttribute("category", categoryOptional.get());
      return "categoriesAdd";
    } else {
      return "404";
    }
  }

  // PRODUCT ROUTES

  @GetMapping("/admin/products")
  public String getAllProduct(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    return "products";
  }

  @GetMapping("/admin/products/add")
  public String fetchAddedProducts(Model model) {
    model.addAttribute("productDTO", new ProductDTO());
    model.addAttribute("categories", categoryService.getAllCategory());
    return "productsAdd";
  }

  @PostMapping("admin/products/add")
  public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
      @RequestParam("productImage")MultipartFile file,
    @RequestParam("imgName")String imgName) throws IOException {
    Product product = new Product();
    product.setId(productDTO.getId());
    product.setDescription(productDTO.getDescription());
    product.setPrice(productDTO.getPrice());
    product.setWeight(productDTO.getWeight());
    product.setName(productDTO.getName());
    product.setCategory(categoryService.fetchCategoryById(productDTO.getCategoryId()).get());

    String imageUUID;

    if (!file.isEmpty()){
      imageUUID = file.getOriginalFilename();
        Path fileNameAndPath =  Paths.get(uploadDir, imageUUID);
        Files.write(fileNameAndPath, file.getBytes());
    } else {
      imageUUID = imgName;
    }
    product.setImageName(imageUUID);
    productService.addProduct(product);
    return "redirect:/admin/products";
  }

  @GetMapping("/admin/products/delete/{id}")
  public String removeProduct(@PathVariable String id){
    productService.removeProductById(id);
    return "redirect:/admin/products";
  }

  @GetMapping("/admin/products/update/{id}")
  public String updateAndGetProducts(@PathVariable String id, Model model) {
    Product product = productService.getProductById(id).get();
    ProductDTO productDTO = new ProductDTO();
    productDTO.setId(product.getId());
    productDTO.setName(product.getName());
    productDTO.setPrice(product.getPrice());
    productDTO.setDescription(product.getDescription());
    productDTO.setWeight(product.getWeight());
    productDTO.setImageName(product.getImageName());
    productDTO.setCategoryId(product.getCategory().getId());

    model.addAttribute("categories", categoryService.getAllCategory());
    model.addAttribute("productDTO", productDTO);
    return "productsAdd";
  }
}
