package com.ff.RealProjectDemoFF.service;


import com.ff.RealProjectDemoFF.dto.ApiResponse;
import com.ff.RealProjectDemoFF.dto.UserDto;

import org.jetbrains.annotations.NotNull;

import org.springframework.http.ResponseEntity;


public interface UserService {



    public ResponseEntity<ApiResponse> saveUser( @NotNull UserDto userDto);

    public ResponseEntity<ApiResponse> getAllUsers(int pageNo, int pageSize);

    public ResponseEntity<ApiResponse> getAllActiveUsers();

    public ResponseEntity<ApiResponse> getAllActiveUsersBasedOnAssigneeGroup(String group);

    public ResponseEntity<ApiResponse> verifyUser(String token);

    public ResponseEntity<ApiResponse> findUserById(String id);

    public ResponseEntity<ApiResponse> findUserByEmailAndPassword (String email, String password);

    public ResponseEntity<ApiResponse> findUserByEmail(String email);

    public ResponseEntity<ApiResponse> setPassword(String email, String password);

    public ResponseEntity<ApiResponse> updatePrivilege(String id, String privilege);

}
