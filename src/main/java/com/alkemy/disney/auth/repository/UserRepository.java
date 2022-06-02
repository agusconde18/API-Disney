package com.alkemy.disney.auth.repository;

import com.alkemy.disney.auth.entity.UserDat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserDat, Long> {

    Optional<UserDat> findByUsernameAndPassword(String username,String password);

    Optional<UserDat> findByUsername(String username);

    Optional<UserDat> findByToken (String token);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
