/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.User;
import java.util.List;


public interface DaoUser {

    public User addUser(User newUser);
    
    public List<User> getAllUsers();
    
    public User getUser (int user_id);
    
    public User editUser(User user);

    public void deleteUser(String username);

    

}
