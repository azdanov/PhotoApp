package org.js.azdanov.api.users.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserResponse {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
}
