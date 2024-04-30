package com.ff.RealProjectDemoFF.repository;

import com.ff.RealProjectDemoFF.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
