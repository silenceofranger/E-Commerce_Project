package com.eshop.api.global;

import com.eshop.api.model.Product;
import java.util.ArrayList;
import java.util.List;

public class GlobalData {
  public static List<Product> cart;
  static {
    cart = new ArrayList<>();
  }

}
