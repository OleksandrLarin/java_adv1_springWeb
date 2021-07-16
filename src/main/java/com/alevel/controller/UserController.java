package com.alevel.controller;

import com.alevel.dto.UserDto;
import com.alevel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("users")
    public UserDto create(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    @PutMapping("users")
    public UserDto update(@RequestBody UserDto dto) {
        Optional<UserDto> result = userService.update(dto);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return result.get();
    }

    @DeleteMapping("users")
    public void delete(@RequestParam Integer id) {
        userService.delete(id);
    }

}
