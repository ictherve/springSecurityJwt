package io.learning.springsecurityjwt.dataaccess.mapper;

import io.learning.springsecurityjwt.dataaccess.entity.UserEntity;
import io.learning.springsecurityjwt.model.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User mapToModel(UserEntity userEntity) {

        if(ObjectUtils.isEmpty(userEntity))
            return null;
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setBirthDay(userEntity.getBirthDay());
        user.setAccountNonExpired(userEntity.getAccountNonExpired());
        user.setEnabled(userEntity.getEnabled());
        user.setAccountNonLocked(userEntity.getAccountNonLocked());
        user.setCredentialsNonExpired(userEntity.getCredentialsNonExpired());

        if(ObjectUtils.isNotEmpty(userEntity.getRoles())) {
            user.setRoles(userEntity.getRoles().stream().map(roleMapper::mapToModel).collect(Collectors.toSet()));
        }
        return user;
    }

    public UserEntity mapToEntity(User user) {
        if(ObjectUtils.isEmpty(user))
            return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setBirthDay(user.getBirthDay());
        userEntity.setAccountNonExpired(user.getAccountNonExpired());
        userEntity.setEnabled(user.getEnabled());
        userEntity.setAccountNonLocked(user.getAccountNonLocked());
        userEntity.setCredentialsNonExpired(user.getCredentialsNonExpired());

        if(ObjectUtils.isNotEmpty(user.getRoles())) {
            userEntity.setRoles(user.getRoles().stream().map(roleMapper::mapToEntity).collect(Collectors.toSet()));
        }
        return userEntity;
    }
}
