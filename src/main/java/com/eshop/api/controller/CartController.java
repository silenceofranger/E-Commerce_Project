package com.eshop.api.controller;

import com.eshop.api.global.GlobalData;
import com.eshop.api.model.Product;
import com.eshop.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
  @Autowired
  ProductService productService;

  @GetMapping("/addToCart/{id}")
  public String addToCart(@PathVariable String id){
    GlobalData.cart.add(productService.getProductById(id).get());
    return "redirect:/shop";
  }

  @GetMapping("/cart")
  public String getCart(Model model){
    model.addAttribute("cartCount", GlobalData.cart.size());
    model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
    model.addAttribute("cart", GlobalData.cart);
    return "/cart";
  }

  @GetMapping("/cart/removeItem/{index}")
  public String removeItemFromCart(@PathVariable int index){
    GlobalData.cart.remove(index);
    return "redirect:/cart";
  }

  @GetMapping("/checkout")
  public String checkout(Model model){
    model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
    return "checkout";
  }


}
