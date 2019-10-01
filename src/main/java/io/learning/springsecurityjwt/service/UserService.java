package io.learning.springsecurityjwt.service;

import io.learning.springsecurityjwt.model.User;

import java.util.List;

public interface UserService {

    User save(User user) throws Exception;
    List<User> findAll();
    void delete(Long id);
}
