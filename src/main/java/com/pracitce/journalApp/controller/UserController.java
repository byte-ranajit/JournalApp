package com.pracitce.journalApp.controller;

import com.pracitce.journalApp.entity.User;
import com.pracitce.journalApp.service.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(ObjectId id) {
        
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{userName}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userName) {

        User existingUser = userService.findByuserName(userName);
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

}
