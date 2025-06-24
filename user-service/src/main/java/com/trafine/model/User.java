package com.trafine.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trafine_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role;
}