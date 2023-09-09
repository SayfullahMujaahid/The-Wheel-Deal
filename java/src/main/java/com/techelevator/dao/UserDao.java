package com.techelevator.dao;

import com.techelevator.security.model.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    List<User> getUsersByWorkOrderId(int workOrderId);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

    User updateUser(User user);






}
