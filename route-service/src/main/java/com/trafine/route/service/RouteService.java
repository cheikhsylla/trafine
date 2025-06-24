package com.trafine.route.service;
import com.trafine.route.model.Route;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RouteService {
  public Route calculateRoute(String from, String to, boolean avoidTolls) {
    return new Route(
      avoidTolls ? "Itinéraire sans péage" : "Itinéraire standard",
      12.4,
      18.6
    );
  }

  public List<Route> getAlternatives(String from, String to) {
    return List.of(
      new Route("Rapide", 10.2, 15.0),
      new Route("Scénique", 13.1, 20.5)
    );
  }
}