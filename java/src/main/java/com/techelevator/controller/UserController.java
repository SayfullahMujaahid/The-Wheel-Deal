package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import com.techelevator.model.UserDto;
import com.techelevator.security.controller.AuthenticationController;
import com.techelevator.security.model.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthenticationController authenticationController;

    @RequestMapping(path = "/workorder/{workOderId}/users", method = RequestMethod.GET)
    public List<User> getUsersByWorkOrderId (int workOrderId) {
        return userDao.getUsersByWorkOrderId(workOrderId);
    }

    @RequestMapping(path = "/profile/updateuser", method = RequestMethod.PUT    )
    public User updateUser(Principal principal, @Valid @RequestBody User user) {
        if (!principal.getName().equals(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot update another user's profile.");
        } else {
            user.setId(userDao.getUserByUsername(principal.getName()).getId());
            return userDao.updateUser(user);
        }
    }

    @RequestMapping(path = "/employeedashboard/createuser", method = RequestMethod.POST)
    public User createUser(Principal principal, @Valid @RequestBody RegisterUserDto registerUserDto) {
        if (!userDao.getUserByUsername(principal.getName()).getRole().equalsIgnoreCase("Admin")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized to create a new User Profile.");
        } else {
            authenticationController.register(registerUserDto);
            return userDao.getUserByUsername(registerUserDto.getUsername());
        }
    }

    //to create user, see AuthenticationController in security package
}
