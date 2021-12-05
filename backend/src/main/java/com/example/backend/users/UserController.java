/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.backend.users;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.message.Message;

/**
 *
 * @author nikola
 */
@CrossOrigin
@Validated
@RestController
@RequestMapping(path = "api/v1/users/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List getUsers() {
        Message message = new Message(200, true, "Users successfully fetched from the database!");
        return List.of(message, userService.getUsers());
    }

    @GetMapping(path = "{id}")
    public List getUserById(@PathVariable("id") Long id) {
        Message message = new Message(200, true, "User with the id: " + id + " successfully found!");
        return List.of(message, userService.getUserById(id));
    }

    @PostMapping()
    public List addNewUser(@RequestBody User user, Message message) {
        message.setStatusCode(201);
        message.setSuccess(true);
        message.setMessage("User successfully created!");
        userService.createNewUser(user, message);
        return List.of(message, user);
    }

    @PutMapping(path = "{id}")
    public Message editUser(@PathVariable("id") Long id, @RequestBody User user, Message message) {
        message.setStatusCode(200);
        message.setSuccess(true);
        message.setMessage("User with the id: " + id + " successfully updated!");
        userService.updateUser(id, user, message);
        return message;
    }

    @DeleteMapping(path = "{id}")
    public Message deleteUser(@PathVariable("id") Long id, Message message) {
        message.setMessage("User with the id: " + id + " successfully deleted!");
        message.setStatusCode(200);
        message.setSuccess(Boolean.TRUE);
        userService.deleteUser(id, message);
        return message;

    }

}
