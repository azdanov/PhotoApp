package org.js.azdanov.api.users.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.js.azdanov.api.users.data.UserEntity;
import org.js.azdanov.api.users.data.UsersRepository;
import org.js.azdanov.api.users.exception.UsersServiceException;
import org.js.azdanov.api.users.io.AlbumsServiceClient;
import org.js.azdanov.api.users.shared.UserDto;
import org.js.azdanov.api.users.ui.model.AlbumResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AlbumsServiceClient albumsServiceClient;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        usersRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getByEmail(String email) {
        return usersRepository.findByEmail(email)
            .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
            .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        return usersRepository.findByUserId(userId)
            .map(userEntity -> {
                List<AlbumResponse> albumsResponse = albumsServiceClient.getAlbums(userId);
                UserDto userDto = modelMapper.map(userEntity, UserDto.class);
                userDto.setAlbums(albumsResponse);

                return userDto;
            })
            .orElseThrow(() -> new UsersServiceException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username)
            .map(userEntity -> new User(
                userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                List.of()))
            .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
