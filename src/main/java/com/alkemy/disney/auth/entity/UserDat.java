package com.alkemy.disney.auth.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
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

    private boolean deleted = false;

    @ManyToMany
    Set<Autorities> roles;
}