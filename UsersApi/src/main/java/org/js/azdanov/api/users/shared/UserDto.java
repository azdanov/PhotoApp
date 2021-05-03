package org.js.azdanov.api.users.shared;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = -8667478471511592064L;

    String userId;
    String email;
    String password;
    String encryptedPassword;
    String firstName;
    String lastName;
}
