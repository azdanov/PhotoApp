package org.js.azdanov.api.users.ui.model;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class CreateUserRequest {
    @NotBlank
    @Email
    @Size(max = 120)
    String email;

    @NotBlank
    @Size(min = 6, max = 255)
    String password;

    @NotBlank
    @Size(max = 50)
    String firstName;

    @NotBlank
    @Size(max = 50)
    String lastName;
}
