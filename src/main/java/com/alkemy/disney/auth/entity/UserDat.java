package com.alkemy.disney.auth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String email;

    String username;

    String password;

    boolean enabled;

    String token;

    @ManyToMany
    Set<Autorities> roles;
}