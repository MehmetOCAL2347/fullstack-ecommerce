package com.fullstackdeneme.business.abstracts;

import com.fullstackdeneme.business.requests.CreateNewUserRequest;
import com.fullstackdeneme.business.responses.UserInfoResponse;
import com.fullstackdeneme.entities.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IUserService{

    ResponseEntity<?> save(CreateNewUserRequest createNewUserRequest);
    ResponseEntity<?> changeUserRole(Role newRole, String userName);
    UserInfoResponse getUserById(Long userName);
    UserInfoResponse getUserByUserName(String userName);
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> login(HttpServletRequest request);

    /*
    saveuser
    changerole
    findbyusername
    deleteuser
    findallusers
     */



}
