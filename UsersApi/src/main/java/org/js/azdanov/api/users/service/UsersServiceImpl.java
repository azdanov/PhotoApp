package org.js.azdanov.api.users.service;

import lombok.RequiredArgsConstructor;
import org.js.azdanov.api.users.data.UserEntity;
import org.js.azdanov.api.users.data.UsersRepository;
import org.js.azdanov.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID());
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        usersRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDto.class);
    }
}
