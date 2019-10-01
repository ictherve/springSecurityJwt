package io.learning.springsecurityjwt.service.impl;

import io.learning.springsecurityjwt.dataaccess.entity.RoleEntity;
import io.learning.springsecurityjwt.dataaccess.mapper.RoleMapper;
import io.learning.springsecurityjwt.dataaccess.repository.RoleRepository;
import io.learning.springsecurityjwt.model.Role;
import io.learning.springsecurityjwt.service.RoleService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role save(Role role) {

        if(ObjectUtils.isEmpty(role))
            throw new IllegalArgumentException("ROLE CANNOT EMPTY");

        if(ObjectUtils.isEmpty(role.getAuthority()))
            throw new IllegalArgumentException("AUTHORITY CANNOT EMPTY");
        if(ObjectUtils.isNotEmpty(roleRepository.findByAuthority(role.getAuthority())))
            throw new IllegalArgumentException("AUTHORITY ALREADY EXISTS");
        RoleEntity roleEntity = roleMapper.mapToEntity(role);
        roleEntity = roleRepository.save(roleEntity);
        return roleMapper.mapToModel(roleEntity);
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> roles =  roleRepository.findAll();
        return ObjectUtils.isNotEmpty(roles) ? roles.stream().map(roleMapper::mapToModel).collect(Collectors.toList()) : null;
    }

    @Override
    public void delete(Long id) {
        Optional<RoleEntity> optional = roleRepository.findById(id);
        if(optional.isPresent())
            roleRepository.delete(optional.get());
    }

    @Override
    public Role createIfNotExist(Role role) {
        if(ObjectUtils.isEmpty(role))
            throw new IllegalArgumentException("ROLE CANNOT EMPTY");

        if(ObjectUtils.isEmpty(role.getAuthority()))
            throw new IllegalArgumentException("AUTHORITY CANNOT EMPTY");

        RoleEntity roleEntity = roleRepository.findByAuthority(role.getAuthority());

        if(ObjectUtils.isNotEmpty(roleEntity))
            return roleMapper.mapToModel(roleEntity);

        roleEntity = roleMapper.mapToEntity(role);
        roleEntity = roleRepository.save(roleEntity);
        return roleMapper.mapToModel(roleEntity);
    }
}
