package com.fullstackdeneme.webapi.controllers;

import com.fullstackdeneme.business.abstracts.IUserService;
import com.fullstackdeneme.business.requests.CreateNewUserRequest;
import com.fullstackdeneme.business.responses.UserInfoResponse;
import com.fullstackdeneme.entities.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin("*")  // Burayı sonradan güncellemek gerekebilir çünkü corsu çiğnemiş oldukk!!!
public class UsersController {

    private IUserService userService;

    @PostMapping("/save")
    private ResponseEntity<?> save(@RequestBody @Valid CreateNewUserRequest createNewUserRequest){
        return this.userService.save(createNewUserRequest);
    }

    @GetMapping("/id/{id}")
    public UserInfoResponse getUserById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }

    @GetMapping("/userName/{userName}")
    public UserInfoResponse getUserByUserName(@PathVariable String userName){
        return this.userService.getUserByUserName(userName);
    }

    @PutMapping("/changeRole/{newRole}/{userName}")
    public ResponseEntity<?> changeUserRole(@PathVariable Role newRole, @PathVariable String userName){
        return this.userService.changeUserRole(newRole, userName);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request){
        return this.userService.login(request);
    }

}
