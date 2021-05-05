package org.js.azdanov.api.albums.io.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.js.azdanov.api.albums.data.AlbumEntity;
import org.js.azdanov.api.albums.service.AlbumsService;
import org.js.azdanov.api.albums.ui.model.AlbumResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/albums")
@Slf4j
@RequiredArgsConstructor
public class AlbumsController {

    private final AlbumsService albumsService;

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> userAlbums(@PathVariable String userId) {

        List<AlbumEntity> albumsEntities = albumsService.getAlbums(userId);

        if (albumsEntities == null || albumsEntities.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }

        Type listType = new TypeToken<List<AlbumResponse>>() {}.getType();
        List<AlbumResponse> albumResponses = new ModelMapper().map(albumsEntities, listType);
        log.info("Returning {} albums", albumResponses.size());

        return ResponseEntity.ok(albumResponses);
    }
}
