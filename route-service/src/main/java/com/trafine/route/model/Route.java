package com.trafine.route.model;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
  private String summary;
  private double distance;
  private double duration;
}