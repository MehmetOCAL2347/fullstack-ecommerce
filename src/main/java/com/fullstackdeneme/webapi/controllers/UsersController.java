package com.fullstackdeneme.webapi.controllers;

import com.fullstackdeneme.business.abstracts.IUserService;
import com.fullstackdeneme.business.requests.CreateNewUserRequest;
import com.fullstackdeneme.business.responses.UserInfoResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UsersController {

    private IUserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody @Valid CreateNewUserRequest createNewUserRequest){
        this.userService.saveUser(createNewUserRequest);
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUserById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }

}
