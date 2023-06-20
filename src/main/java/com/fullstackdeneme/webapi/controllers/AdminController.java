package com.fullstackdeneme.webapi.controllers;

import com.fullstackdeneme.business.abstracts.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private IUserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        return this.userService.deleteUserById(id);
    }

}
