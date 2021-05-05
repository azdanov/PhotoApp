package org.js.azdanov.api.users.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponse> albums;
}
