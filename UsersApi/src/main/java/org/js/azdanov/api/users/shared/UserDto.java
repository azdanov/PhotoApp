package org.js.azdanov.api.users.shared;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = -8667478471511592064L;

    UUID userId;
    String email;
    String password;
    String encryptedPassword;
    String firstName;
    String lastName;
}
