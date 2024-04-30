package com.ff.RealProjectDemoFF.controller;

import com.ff.RealProjectDemoFF.document.User;
import com.ff.RealProjectDemoFF.dto.ApiResponse;
import com.ff.RealProjectDemoFF.dto.UserDto;
import com.ff.RealProjectDemoFF.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDto userDto){
       return userService.saveUser(userDto);
    }

    @PostMapping("/verification/{token}")
    public  ResponseEntity<ApiResponse> verify(@PathVariable String token){
        return  userService.verifyUser(token);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllUsers(@RequestParam(defaultValue = "0") int pageNo,
                                                   @RequestParam(defaultValue = "10") int pageSize){
        return userService.getAllUsers(pageNo, pageSize);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestHeader String email, @RequestHeader String password){
        return userService.findUserByEmailAndPassword(email,password);
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<ApiResponse> getByEmail(@RequestHeader String email){
        return userService.findUserByEmail(email);
    }

    @PostMapping("/setPassword")
    public ResponseEntity<ApiResponse> setPassword(@RequestHeader String email , @RequestHeader String password){
        return userService.setPassword(email,password);
    }

    @PatchMapping("/updatePrivilege/{id}")
    ResponseEntity<ApiResponse> updatePrivilege (@PathVariable String id, @RequestHeader String password){
        return userService.updatePrivilege(id,password);
    }

    @GetMapping("/getAllActiveUsers")
    ResponseEntity<ApiResponse> getAllActiveUsers(){
        return userService.getAllActiveUsers();
    }

    @GetMapping("/getAllActiveUsersBasedOnAssigneeGroup")
    ResponseEntity<ApiResponse> getAllActiveUsersBasedOnAssigneeGroup(@RequestParam String group){
        return userService.getAllActiveUsersBasedOnAssigneeGroup(group);
    }
}
