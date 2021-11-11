package com.eshop.api.service;

import com.eshop.api.model.CustomUserDetail;
import com.eshop.api.model.User;
import com.eshop.api.repository.UsersRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
@Autowired
  UsersRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByEmail(email);
    user.orElseThrow(() -> new UsernameNotFoundException("can't find user"));
    return user.map(CustomUserDetail::new).get();
  }
}
