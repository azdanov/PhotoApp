package org.js.azdanov.api.users.io;

import org.js.azdanov.api.users.ui.model.AlbumResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumsFallback implements AlbumsServiceClient {

    @Override
    public List<AlbumResponse> getAlbums(String userId) {
        return List.of();
    }
}
