package org.js.azdanov.api.users.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateUserResponse {
    UUID userId;
    String email;
    String firstName;
    String lastName;
}
