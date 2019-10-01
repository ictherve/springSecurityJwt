package io.learning.springsecurityjwt.service.impl;

import io.learning.springsecurityjwt.dataaccess.entity.UserEntity;
import io.learning.springsecurityjwt.dataaccess.mapper.UserMapper;
import io.learning.springsecurityjwt.dataaccess.repository.UserRepository;
import io.learning.springsecurityjwt.model.Role;
import io.learning.springsecurityjwt.model.User;
import io.learning.springsecurityjwt.security.authenticationfacade.AuthenticationFacadeService;
import io.learning.springsecurityjwt.service.RoleService;
import io.learning.springsecurityjwt.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder encoder, RoleService roleService, AuthenticationFacadeService authenticationFacadeService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.roleService = roleService;
        this.authenticationFacadeService = authenticationFacadeService;
    }

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;
    private final AuthenticationFacadeService authenticationFacadeService;

    @Override
    public User save(User user) throws Exception {

        if(ObjectUtils.isEmpty(user))
            throw new IllegalArgumentException("USER CANNOT BE NULL");
        if(ObjectUtils.isEmpty(user.getEmail()))
            throw new IllegalArgumentException("EMAIL CANNOT BE NULL");
        if(ObjectUtils.isEmpty(user.getUsername()))
            throw new IllegalArgumentException("USERNAME CANNOT BE NULL");
        if(ObjectUtils.isNotEmpty(userRepository.findByEmail(user.getEmail())) ||
                ObjectUtils.isNotEmpty(userRepository.findByUsername(user.getUsername())))
            throw new Exception("USERNAME OR EMAIL ALREADY EXISTS");

        setParameters(user);
        user.addRole(roleService.createIfNotExist(new Role("ROLE_USER")));
        UserEntity userEntity = userMapper.mapToEntity(user);
        userEntity = userRepository.save(userEntity);

        return userMapper.mapToModel(userEntity);
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return ObjectUtils.isNotEmpty(users) ? users.stream().map(userMapper::mapToModel).collect(Collectors.toList()) : null ;
    }

    @Override
    public void delete(Long id) {

        String username = authenticationFacadeService.getAuthenticated();

        if(ObjectUtils.isNotEmpty(username)) {
            Optional<UserEntity> optional = userRepository.findById(id);
            userRepository.delete(optional.get());
        }

    }

    private void initParameters(User user) {
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
    }

    private void setParameters(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        initParameters(user);
    }
}
