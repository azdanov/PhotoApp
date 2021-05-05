package org.js.azdanov.api.users.ui.controller;

import lombok.RequiredArgsConstructor;
import org.js.azdanov.api.users.service.UsersService;
import org.js.azdanov.api.users.shared.UserDto;
import org.js.azdanov.api.users.ui.model.CreateUserRequest;
import org.js.azdanov.api.users.ui.model.CreateUserResponse;
import org.js.azdanov.api.users.ui.model.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto createdUser = usersService.createUser(userDto);
        CreateUserResponse userResponse = modelMapper.map(createdUser, CreateUserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {

        UserDto userDto = usersService.getUserByUserId(userId);
        UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);

        return ResponseEntity.ok().body(userResponse);
    }
}
