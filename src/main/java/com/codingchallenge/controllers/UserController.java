package com.codingchallenge.controllers;

import com.codingchallenge.dto.OrganizationDto;
import com.codingchallenge.dto.UserDto;
import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import com.codingchallenge.services.UserService;
import com.codingchallenge.utils.Converters;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users", produces = "application/json")
public class UserController {

    private UserService userService;
    private Converters converters;

    public UserController(UserService userService, Converters converters) {
        this.userService = userService;
        this.converters = converters;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = converters.convertToModel(userDto);
        return converters.convertToDto(userService.createUser(user));
    }

    @GetMapping("/{userId}")
    public Set<OrganizationDto> getOrganizationsFromUser(@PathVariable Long userId) {
        Set<Organization> organizations = userService.getOrganizationsFromUser(userId);
        return organizations.stream()
                .map(converters::convertToDto)
                .collect(Collectors.toSet());
    }
}
