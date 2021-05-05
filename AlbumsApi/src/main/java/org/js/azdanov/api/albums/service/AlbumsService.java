package org.js.azdanov.api.albums.service;

import org.js.azdanov.api.albums.data.AlbumEntity;

import java.util.List;

public interface AlbumsService {
    List<AlbumEntity> getAlbums(String userId);
}
