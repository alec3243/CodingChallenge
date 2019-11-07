package com.codingchallenge.controllers;

import com.codingchallenge.dto.OrganizationDto;
import com.codingchallenge.dto.UserDto;
import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import com.codingchallenge.services.UserService;
import com.codingchallenge.utils.Converter;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users", produces = "application/json")
public class UserController {

    private UserService userService;
    private Converter converter;

    public UserController(UserService userService, Converter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = converter.convertToModel(userDto);
        return converter.convertToDto(userService.createUser(user));
    }

    @GetMapping("/{userId}")
    public Set<OrganizationDto> getOrganizationsFromUser(@PathVariable Long userId) {
        Set<Organization> organizations = userService.getOrganizationsFromUser(userId);
        return organizations.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toSet());
    }
}
