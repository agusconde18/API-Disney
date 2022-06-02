package com.alkemy.disney.auth.repository;

import com.alkemy.disney.auth.entity.Autorities;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoritiesRepository extends JpaRepository<Autorities, Long> {

    Autorities findByRole( String role );

    boolean existsByRole ( String role );
}
