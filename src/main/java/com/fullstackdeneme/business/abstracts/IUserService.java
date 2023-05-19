package com.fullstackdeneme.business.abstracts;

import com.fullstackdeneme.business.requests.CreateNewUserRequest;
import com.fullstackdeneme.business.responses.UserInfoResponse;
import com.fullstackdeneme.entities.Role;

public interface IUserService {

    void saveUser(CreateNewUserRequest createNewUserRequest);
    void changeUserRole(Role newRole, String userName);
    UserInfoResponse getUserById(Long userName);

    /*
    saveuser
    changerole
    findbyusername
    deleteuser
    findallusers
     */



}
