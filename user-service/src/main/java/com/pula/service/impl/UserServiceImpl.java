package com.pula.service.impl;

import com.pula.exception.UserException;
import com.pula.model.User;
import com.pula.repository.UserRepository;
import com.pula.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User not found with this id "+id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return "User deleted";
        }
        throw new UserException("User is not found with this id "+id);
    }

    @Override
    public User updateUser(Long id, User user) throws UserException {
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isEmpty()){
            throw new UserException("User not found with this id "+id);
        }
        User updatedUser = existingUser.get();
        updatedUser.setFullName(user.getFullName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRole(user.getRole());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setUserName(user.getUserName());
        return userRepository.save(updatedUser);
    }
}
