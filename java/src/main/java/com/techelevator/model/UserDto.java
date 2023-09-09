package com.techelevator.model;

import com.techelevator.security.model.Authority;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private Set<Authority> authorities = new HashSet<>();
    private String email;
    private String phoneNumber;
    private String badge;


    public UserDto () {

    }

    public UserDto(String username, String firstName, String lastName,
                   String password, String role, Set<Authority> authorities,
                   String email, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.badge = "";

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

}
