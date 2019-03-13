/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Reid
 */
public class DaoUserImpl implements DaoUser {
    
    private static final String SQL_INSERT_USER
        = "insert into users (username, password, enabled) values (?, ?, 1)";
    private static final String SQL_INSERT_AUTHORITY
        = "insert into authorities (username, authority) values (?, ?)";
    private static final String SQL_DELETE_USER
        = "delete from users where username = ?";
    private static final String SQL_DELETE_AUTHORITIES
        = "delete from authorities where username = ?";
    private static final String SQL_GET_ALL_USERS
        = "select * from users";
    private static final String SQL_GET_USER
        = "select * from users where user_id = ?";
    private static final String SQL_EDIT_USER
            = "update users set "
            + "username = ?, password = ? "
            + "where user_id = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public User addUser(User newUser) {
        jdbcTemplate.update(SQL_INSERT_USER, 
                            newUser.getUsername(), 
                            newUser.getPassword());
        newUser.setUser_id(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                                   Integer.class));

        
        ArrayList<String> authorities = newUser.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, 
                                newUser.getUsername(), 
                                authority);
        }

        return newUser;
    }
    
    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }

    @Override
    public User getUser(int user_id) {
        
        try {
            return jdbcTemplate.queryForObject(SQL_GET_USER, new UserMapper(), user_id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    @Transactional
    public User editUser(User user) {
        User oldUser = getUser(user.getUser_id());
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, oldUser.getUsername());
        jdbcTemplate.update(SQL_EDIT_USER, user.getUsername(), user.getPassword(), user.getUser_id());
        
        ArrayList<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, user.getUsername(), authority);
        }
        return user;
    }
    
    @Override
    @Transactional
    public void deleteUser(String username) {
        
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }

    

    

    private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUser_id(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

}
