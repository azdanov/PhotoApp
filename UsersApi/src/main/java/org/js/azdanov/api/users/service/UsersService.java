package org.js.azdanov.api.users.service;

import org.js.azdanov.api.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getByEmail(String email);
}
