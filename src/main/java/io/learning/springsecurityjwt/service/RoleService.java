package io.learning.springsecurityjwt.service;

import io.learning.springsecurityjwt.model.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);
    List<Role> findAll();
    void delete(Long id);
    Role createIfNotExist(Role role);
}
