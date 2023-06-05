package com.fullstackdeneme.business.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateNewUserRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
