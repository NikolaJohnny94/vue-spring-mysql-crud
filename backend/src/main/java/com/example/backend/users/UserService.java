/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.backend.users;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.message.Message;

/**
 *
 * @author nikola
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List getUsers() {
        return userRepository.findAll();
    }

    public List getUserById(Long id) {
        User userFoundById = userRepository.findById(id).orElseThrow(()-> new IllegalStateException("User with the id: " + id + " not found in the database!"));
        return List.of(userFoundById);
    }

    public List createNewUser(User user, Message message) {
        userRepository.save(user);
        return List.of(user, message);
    }

    @Transactional
    public Message updateUser(Long id, User user, Message message) {
        User userFoundById = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with the id: " + id + " not found in the database!"));
        userFoundById.setFirstname(user.firstname);
        userFoundById.setLastname(user.lastname);
        userFoundById.setEmail(user.email);
        userFoundById.setCountry(user.country);
        userFoundById.setCity(user.city);
        return message;
    }

    public Message deleteUser(Long id, Message message) {
        User userFoundById = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with the id: " + id + " not found in the database!"));
        userRepository.delete(userFoundById);
        return message;
    }

}
