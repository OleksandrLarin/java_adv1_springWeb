package com.alevel.service;

import com.alevel.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto createUser(UserDto dto);

    Optional<UserDto> update(UserDto dto);

    void delete(Integer id);
}
