package com.barriSenseBack.barrisense_feedback_api.repository;

import com.barriSenseBack.barrisense_feedback_api.entity.Role;
import com.barriSenseBack.barrisense_feedback_api.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
