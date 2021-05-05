package org.js.azdanov.api.users.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumResponse {
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
