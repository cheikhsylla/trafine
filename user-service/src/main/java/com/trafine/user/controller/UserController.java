package com.trafine.user.controller;
import com.trafine.user.entity.User;
import com.trafine.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired private UserService userService;

  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PutMapping("/{id}")
  public User update(@PathVariable Long id, @RequestBody User user) {
    return userService.update(id, user);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }
}