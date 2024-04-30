package com.ff.RealProjectDemoFF.dao;

import com.ff.RealProjectDemoFF.document.User;

import com.ff.RealProjectDemoFF.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public Page<User> getAllUsers(int pageNo, int pageSize){
        return userRepository.findAll(PageRequest.of(pageNo,pageSize));
    }

    @Override
    public List<User> getAllActiveUsers() {
        Query query = Query.query(Criteria.where("privilege").nin("Admin","Customer").and("status").is("Active"));
        return mongoTemplate.find(query,User.class,"user");
    }

    @Override
    public List<User> getAllActiveUsersBasedOnAssigneeGroup(String group) {
        Query query = Query.query(Criteria.where("privilege").is(group).and("status").is("Active"));

        return  mongoTemplate.find(query,User.class,"user");
    }

    @Override
    public User findUserByToken(String token){
        Query query = Query.query(Criteria.where("token").is(token));
        return mongoTemplate.findOne(query, User.class,"user");
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmailAndPassword(String email, String password){
        Query query = Query.query(Criteria.where("email").is(email).and("password").is(password));
        return mongoTemplate.findOne(query, User.class,"user");
    }

    @Override
    public User findByEmail(String email){
        Query query = Query.query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query,User.class,"user");
    }
}
