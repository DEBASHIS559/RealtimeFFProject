package com.ff.RealProjectDemoFF.dao;

import com.ff.RealProjectDemoFF.document.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserDao {


    public User saveUser(User user);

    public Page<User> getAllUsers(int pageNo, int pageSize);

    public List<User> getAllActiveUsers();

    public List<User> getAllActiveUsersBasedOnAssigneeGroup(String group);

    public User findUserByToken(String token);

    public User findById(String id) ;

    public User findByEmailAndPassword(String email, String password);

    public User findByEmail(String email);
}
