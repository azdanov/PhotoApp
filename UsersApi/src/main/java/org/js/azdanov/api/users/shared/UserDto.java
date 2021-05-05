package org.js.azdanov.api.users.shared;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.js.azdanov.api.users.ui.model.AlbumResponse;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 7469143880900225673L;

    String userId;
    String email;
    String password;
    String encryptedPassword;
    String firstName;
    String lastName;
    List<AlbumResponse> albums;
}
