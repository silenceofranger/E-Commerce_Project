package com.eshop.api.controller;

import com.eshop.api.global.GlobalData;
import com.eshop.api.model.Role;
import com.eshop.api.model.User;
import com.eshop.api.repository.RolesRepository;
import com.eshop.api.repository.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired UsersRepository userRepository;
  @Autowired RolesRepository roleRepository;
  @GetMapping("/login")
  public String login() {

    GlobalData.cart.clear();
    return "login";
  }

  @GetMapping("/register")
  public String registerGet() {
    return "register";
  }

  @PostMapping("/register")
  public String saveUser(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    List<Role> roles = new ArrayList<>();
    roles.add(roleRepository.findById("two").get());
    user.setRoles(roles);
    userRepository.save(user);
    request.login(user.getEmail(), user.getPassword());
    return "redirect:/";
  }
}
