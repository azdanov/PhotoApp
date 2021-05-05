package org.js.azdanov.api.users.io;

import org.js.azdanov.api.users.ui.model.AlbumResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "albums-api", fallback = AlbumsFallback.class)
public interface AlbumsServiceClient {

    @GetMapping("/users/{userId}/albums")
    List<AlbumResponse> getAlbums(@PathVariable String userId);
}
