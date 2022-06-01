package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    @Override
    Optional<Serie> findById(Long aLong);
}
