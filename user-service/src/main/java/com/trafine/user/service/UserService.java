package com.trafine.user.service;
import com.trafine.user.entity.User;
import com.trafine.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public User update(Long id, User user) {
    User existing = findById(id);
    existing.setEmail(user.getEmail());
    existing.setRole(user.getRole());
    return userRepository.save(existing);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }
}