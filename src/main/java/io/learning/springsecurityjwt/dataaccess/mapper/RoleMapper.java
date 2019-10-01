package io.learning.springsecurityjwt.dataaccess.mapper;

import io.learning.springsecurityjwt.dataaccess.entity.RoleEntity;
import io.learning.springsecurityjwt.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role mapToModel(RoleEntity role);

    RoleEntity mapToEntity(Role role);

    List<Role> mapToModels(List<RoleEntity> roles);

    List<Role> mapToEntities(List<Role> roles);

}
