package com.fullstackdeneme.business.concretes;

import com.fullstackdeneme.business.abstracts.IUserService;
import com.fullstackdeneme.business.requests.CreateNewUserRequest;
import com.fullstackdeneme.business.responses.UserInfoResponse;
import com.fullstackdeneme.core.utilities.mappers.IModelMapperService;
import com.fullstackdeneme.dataAccess.UserRepository;
import com.fullstackdeneme.entities.Role;
import com.fullstackdeneme.entities.User;
import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserManager implements IUserService {

    private UserRepository userRepository;
    //private PasswordEncoder passwordEncoder;
    private IModelMapperService modelMapperService;

    @Override
    public void saveUser(CreateNewUserRequest createNewUserRequest) {

        // Business rules

        // DB level jobs
        User user = this.modelMapperService.forRequests().map(createNewUserRequest, User.class);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user.setRole(Role.ROLE_USER);
        this.userRepository.save(user);
    }

    @Override
    public void changeUserRole(Role newRole, String userName) {
        User user = this.userRepository.findByUserName(userName).orElseThrow(()-> new RuntimeException("changeUserRole"));
        user.setRole(newRole);
        this.userRepository.save(user);
    }

    @Override
    public UserInfoResponse getUserById(Long id) {
        //User user = this.userRepository.findByUserName("mehmet").orElseThrow(() -> new RuntimeException("getUserInformation"));
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("getUserInformation"));
        UserInfoResponse userInfoResponse = this.modelMapperService.forResponses().map(user, UserInfoResponse.class);
        return userInfoResponse;
    }
}
