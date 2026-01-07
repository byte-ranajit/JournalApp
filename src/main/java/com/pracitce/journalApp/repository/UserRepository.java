package com.pracitce.journalApp.repository;

import com.pracitce.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByuserName(String userName);
}
