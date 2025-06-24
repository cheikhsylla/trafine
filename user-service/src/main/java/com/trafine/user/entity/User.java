package com.trafine.user.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trafine_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true)
  private String email;

  private String password;
  private String role;

  private String provider;
  private String providerId;
}
