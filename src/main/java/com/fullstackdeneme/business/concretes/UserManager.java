package com.fullstackdeneme.business.concretes;

import com.fullstackdeneme.business.abstracts.IUserService;
import com.fullstackdeneme.business.requests.CreateNewUserRequest;
import com.fullstackdeneme.business.responses.UserInfoResponse;
import com.fullstackdeneme.core.utilities.mappers.IModelMapperService;
import com.fullstackdeneme.dataAccess.UserRepository;
import com.fullstackdeneme.entities.Role;
import com.fullstackdeneme.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserManager implements IUserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private IModelMapperService modelMapperService;

    @Override
    public ResponseEntity<?> save(CreateNewUserRequest createNewUserRequest) {
        // Business rule'a taşınacak
        /*if(this.userRepository.findByUserName(createNewUserRequest.getUserName()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }*/
        User user = this.modelMapperService.forRequests().map(createNewUserRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user.setRole(Role.ROLE_USER);
        this.userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> login(HttpServletRequest request) {
        // Business rules
        Principal principal = request.getUserPrincipal();

        if(principal == null || principal.getName() == null){
            // Logout
            return new ResponseEntity<>(HttpStatus.OK);
        }

        Optional<User> user = this.userRepository.findByuserName(principal.getName());

        // !! User Info Response dönmelii
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeUserRole(Role newRole, String userName) {
        // !! Sadece Admin rolündeki kişi değişiklik yapabilir...
        // Yeni role ile eski rol aynı ise değişiklik yapılmadı şeklinde uyarı verilmeli...


        User user = this.userRepository.findByuserName(userName).orElseThrow(()-> new RuntimeException("changeUserRole"));
        user.setRole(newRole);
        this.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public UserInfoResponse getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("getUserInformation"));
        UserInfoResponse userInfoResponse = this.modelMapperService.forResponses().map(user, UserInfoResponse.class);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse getUserByUserName(String userName) {
        User user = this.userRepository.findByuserName(userName).orElseThrow(() -> new RuntimeException("getUserInfoByUserName"));
        UserInfoResponse userInfoResponse = this.modelMapperService.forResponses().map(user, UserInfoResponse.class);
        return userInfoResponse;
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = this.userRepository.findAll();
        List<UserInfoResponse> allUsersResponse = new LinkedList<>();

        for (User user: allUsers) {
            UserInfoResponse userInfoResponse = this.modelMapperService.forResponses().map(user, UserInfoResponse.class);
            allUsersResponse.add(userInfoResponse);
        }

        return ResponseEntity.ok(allUsersResponse);
    }
}
