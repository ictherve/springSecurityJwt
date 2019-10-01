package io.learning.springsecurityjwt.service.impl;

import io.learning.springsecurityjwt.dataaccess.entity.UserEntity;
import io.learning.springsecurityjwt.dataaccess.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

         UserEntity user = userRepository.findByUsername(s);

         if(ObjectUtils.isNotEmpty(user))
             return user;

        throw new UsernameNotFoundException(s);
    }
}
