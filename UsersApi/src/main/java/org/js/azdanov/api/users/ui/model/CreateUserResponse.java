package org.js.azdanov.api.users.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateUserResponse {
    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;
}
