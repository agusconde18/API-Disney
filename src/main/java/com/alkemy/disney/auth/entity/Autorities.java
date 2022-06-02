package com.alkemy.disney.auth.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "autorities")
@SQLDelete(sql = "UPDATE personaje set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Autorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    String role;
    private boolean deleted = false;

}