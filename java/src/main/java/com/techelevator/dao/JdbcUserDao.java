package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.security.exception.DaoException;
import com.techelevator.security.model.RegisterUserDto;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, first_name, last_name, password_hash, email, phone, role, is_activated  FROM users";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                User user = mapRowToUser(results);
                users.add(user);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return users;
    }//completed

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT user_id, username, first_name, last_name, password_hash, email, phone, role, is_activated FROM users WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                user = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }//completed

    @Override
    public List<User> getUsersByWorkOrderId (int workOrderId) {
        List<User> returnedUsers = new ArrayList<>();
        String sql = "select users.user_id, username, first_name, last_name, role\n" +
                "from users\n" +
                "join users_work_order on users.user_id = users_work_order.user_id\n" +
                "where work_order_id = ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, workOrderId);
            while (rowSet.next()) {
                User user = mapRowToWorkOrderUser(rowSet);
                returnedUsers.add(user);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        }

        return returnedUsers;
    }//completed

    @Override
    public User getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        User user = null;
        String sql = "SELECT user_id, username, first_name, last_name, password_hash, email, phone, role, is_activated FROM users WHERE username = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }//completed

    @Override
    public User createUser(RegisterUserDto user) {
        User newUser = null;
        String insertUserSql = "INSERT INTO users (username, first_name, last_name, password_hash, email, phone, role) values (?, ?, ?, ?, ?, ?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());
//        String ssRole = user.getRole().toUpperCase().startsWith("ROLE_") ? user.getRole().toUpperCase() : "ROLE_" + user.getRole().toUpperCase();
        try {
            int newUserId = jdbcTemplate.queryForObject(insertUserSql, int.class, user.getUsername(),
                    user.getFirstName(), user.getLastname(), password_hash, user.getEmail(),
                    user.getPhone(), user.getRole());
            newUser = getUserById(newUserId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newUser;
    }//completed

    @Override
    public User updateUser(User user) {
        User updatedUser = new User();
        String sql = "update user set username=?, first_name=?, last_name=?, " +
                "password_hash=?, email=?, phone=?, role=?, is_activated=? " +
                "where user_id=?;";

        try {

            int rowsUpdated = jdbcTemplate.update(sql, int.class, user.getUsername(),
                    user.getFirstName(), user.getLastName(), user.getPassword(),
                    user.getEmail(), user.getPhoneNumber(), user.getRole(),
                    user.isActivated(), user.getId());

            if (rowsUpdated == 0) {
                throw new DaoException("Could not update User.");
            } else {
                updatedUser = getUserById(user.getId());
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Could not update User.", e);
        }

        return updatedUser;
    }//completed
    

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhoneNumber(rs.getString("phone"));
        user.setPassword(rs.getString("password_hash"));
        user.setEmail(rs.getString("email"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(rs.getBoolean("is_activated"));
        user.setRole(rs.getString("role"));
        return user;
    }//completed

    private User mapRowToWorkOrderUser (SqlRowSet rowSet) {
        User newUser = new User();
        newUser.setId(rowSet.getInt("user_id"));
        newUser.setUsername(rowSet.getString("username"));
        newUser.setFirstName(rowSet.getString("first_name"));
        newUser.setLastName(rowSet.getString("last_name"));
        newUser.setRole(rowSet.getString("role"));
        //add badge here
        return newUser;
    }//completed
}
