package com.alkemy.disney.auth.repository;

import com.alkemy.disney.auth.entity.EnumRole;
import com.alkemy.disney.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);
}
