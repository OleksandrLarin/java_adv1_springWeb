package com.alevel.service;

import com.alevel.dto.UserDto;
import com.alevel.model.User;
import com.alevel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        Iterable<User> allUsers = userRepository.findAll();

        return convertToDtoList(allUsers);
    }

    private List<UserDto> convertToDtoList(Iterable<User> allUsers) {
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : allUsers) {
            dtoList.add(convertToDto(user));
        }
        return dtoList;
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto(user.getId(), user.getName(), user.getEmail());
        dto.setCountry(user.getCountry());
        dto.setDateOfBirth(user.getDateOfBirth());
        return dto;
    }

    @Override
    public UserDto createUser(UserDto dto) {
        User user = convertToModel(dto);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    private User convertToModel(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCountry(dto.getCountry());
        user.setDateOfBirth(dto.getDateOfBirth());
        return user;
    }

    @Override
    public Optional<UserDto> update(UserDto dto) {
        Optional<User> user = userRepository.findById(dto.getId());

        if (user.isEmpty()) {
            return Optional.empty();
        }
        User savedUser = userRepository.save(updateModel(user.get(), dto));
        UserDto response = convertToDto(savedUser);
        return Optional.of(response);
    }

    private User updateModel(User user, UserDto dto) {
        user.setCountry(dto.getCountry());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setDateOfBirth(dto.getDateOfBirth());

        return user;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @PostConstruct
    public void init() {
        System.out.println("initialization -+++++++++++++++++++++");
    }
}
