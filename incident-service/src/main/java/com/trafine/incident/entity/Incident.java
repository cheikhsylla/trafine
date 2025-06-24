package com.trafine.incident.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trafine_incident")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;
  private String description;
  private double latitude;
  private double longitude;
  private boolean validated = false;
  private LocalDateTime timestamp = LocalDateTime.now();
}