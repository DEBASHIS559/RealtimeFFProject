package com.ff.RealProjectDemoFF.service;

import com.ff.RealProjectDemoFF.dao.UserDao;
import com.ff.RealProjectDemoFF.document.User;
import com.ff.RealProjectDemoFF.dto.ApiResponse;
import com.ff.RealProjectDemoFF.dto.UserDto;
import com.ff.RealProjectDemoFF.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    private final MailService mailService;

    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<ApiResponse> saveUser( @NotNull UserDto userDto){

        User user = modelMapper.map(userDto, User.class);

        user.setId(UUID.randomUUID().toString());

//      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//      user.setCreatedTime(formatter.format(LocalDateTime.now(ZoneId.of("Asia/Kolkata"))));//if time is of String type
        //user.setCreated_on(LocalDateTime.now()); if time is of LocalDateTime type
        user.createEntity(user.getId(),user.getName());
        String token = mailService.getToken();
        user.setToken(token);

        String subject = "Your activation for fireflink";
        String body="your activation link for fireflink is http://localhost:8080/verification/"+token;

        String sentMail = mailService.sendActivationEmail(user.getEmail(),subject,body);

        user.setStatus("Activation Pending");
        User receivedUser = userDao.saveUser(user);

        return ResponseUtil.getCreatedResponse(receivedUser);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllUsers(int pageNo, int pageSize){
        Page<User> receivedUsers = userDao.getAllUsers(pageNo, pageSize);
        return ResponseUtil.getOkResponse(receivedUsers);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllActiveUsers() {
        List<User> receivedUsers = userDao.getAllActiveUsers();
        return  ResponseUtil.getOkResponse(receivedUsers);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllActiveUsersBasedOnAssigneeGroup(String group) {
        List<User> receivedUsers = userDao.getAllActiveUsersBasedOnAssigneeGroup(group);
        return ResponseUtil.getOkResponse(receivedUsers);
    }

    @Override
    public ResponseEntity<ApiResponse> verifyUser(String token){

        User receivedUser = userDao.findUserByToken(token);
        if (receivedUser != null && token.equals(receivedUser.getToken())) {
            receivedUser.setStatus("Active");
            receivedUser.setToken(null);
            receivedUser=userDao.saveUser(receivedUser);

            return ResponseUtil.getCreatedResponse(receivedUser);

        }
        else  throw new NullPointerException();
    }

    @Override
    public ResponseEntity<ApiResponse> findUserById(String id){
        User receivedUser = userDao.findById(id);

        return ResponseUtil.getOkResponse(receivedUser);

    }

    @Override
    public ResponseEntity<ApiResponse> findUserByEmailAndPassword (String email, String password){
        User receivedUser= userDao.findByEmailAndPassword(email,password);
        return ResponseUtil.getOkResponse(receivedUser);
    }

    public ResponseEntity<ApiResponse> findUserByEmail(String email){
        User receivedUser = userDao.findByEmail(email);
        return ResponseUtil.getOkResponse(receivedUser);
    }

    @Override
    public ResponseEntity<ApiResponse> setPassword(String email, String password){
        User receivedUser = userDao.findByEmail(email);
        receivedUser.setPassword(password);
        receivedUser = userDao.saveUser(receivedUser);

        return ResponseUtil.getOkResponse(receivedUser);

    }

    @Override
    public ResponseEntity<ApiResponse> updatePrivilege(String id, String privilege){
        User receivedUser = userDao.findById(id);
        String previousPrivilege = receivedUser.getPrivilege();
        receivedUser.setPrivilege(privilege);
        receivedUser = userDao.saveUser(receivedUser);
        String updatedPrivilege = receivedUser.getPrivilege();

        String subject = "Privilege changed";
        String body= "Hi! "+receivedUser.getName()+" your privilege is changed from "+previousPrivilege+
                " to "+updatedPrivilege+"\n Thank you !!!!!";

        String sentMail = mailService.sendUpdateEmail(receivedUser.getEmail(),subject,body);

        return ResponseUtil.getOkResponse(receivedUser);
    }

}
