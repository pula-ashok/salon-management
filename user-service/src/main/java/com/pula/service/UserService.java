package com.pula.service;

import com.pula.exception.UserException;
import com.pula.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws Exception;
    List<User> getAllUsers();
    String deleteUser(Long id) throws UserException;
    User updateUser(Long id, User user) throws UserException;
}
