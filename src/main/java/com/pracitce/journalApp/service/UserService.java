package com.pracitce.journalApp.service;

import com.pracitce.journalApp.entity.User;
import com.pracitce.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteByid(ObjectId id){
        userRepository.deleteById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User findByuserName(String userName){
        return userRepository.findByuserName(userName);
    }
}
