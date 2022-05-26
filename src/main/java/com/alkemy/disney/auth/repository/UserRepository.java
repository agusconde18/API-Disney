package com.alkemy.disney.auth.repository;

import com.alkemy.disney.auth.entity.UserDat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDat, Long> {

}
