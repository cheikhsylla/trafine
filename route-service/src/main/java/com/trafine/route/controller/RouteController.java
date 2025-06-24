package com.trafine.route.controller;
import com.trafine.route.model.Route;
import com.trafine.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {
  @Autowired private RouteService service;

  @GetMapping
  public Route getRoute(@RequestParam String from, @RequestParam String to,
                        @RequestParam(defaultValue = "false") boolean avoidTolls) {
    return service.calculateRoute(from, to, avoidTolls);
  }

  @GetMapping("/alternatives")
  public List<Route> alternatives(@RequestParam String from, @RequestParam String to) {
    return service.getAlternatives(from, to);
  }
}