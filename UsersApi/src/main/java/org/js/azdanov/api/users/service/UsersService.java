package org.js.azdanov.api.users.service;

import org.js.azdanov.api.users.shared.UserDto;

public interface UsersService {
    UserDto createUser(UserDto userDto);
}
