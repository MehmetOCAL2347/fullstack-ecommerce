package com.fullstackdeneme.business.requests;

import lombok.Data;

@Data
public class CreateNewUserRequest {
    private String name;
    private String userName;
    private String password;
}
