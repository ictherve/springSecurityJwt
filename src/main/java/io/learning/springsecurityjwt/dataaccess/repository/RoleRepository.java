package io.learning.springsecurityjwt.dataaccess.repository;

import io.learning.springsecurityjwt.dataaccess.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByAuthority(String authority);
}
