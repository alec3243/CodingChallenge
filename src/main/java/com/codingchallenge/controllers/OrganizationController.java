package com.codingchallenge.controllers;

import com.codingchallenge.dto.OrganizationDto;
import com.codingchallenge.dto.UserDto;
import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import com.codingchallenge.services.OrganizationService;
import com.codingchallenge.utils.Converters;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/organizations", produces = "application/json")
public class OrganizationController {

    private OrganizationService organizationService;
    private Converters converters;

    public OrganizationController(OrganizationService organizationService, Converters converters) {
        this.organizationService = organizationService;
        this.converters = converters;
    }

    @PostMapping
    public OrganizationDto createOrganization(@RequestBody OrganizationDto organizationDto) {
        Organization organization = converters.convertToModel(organizationDto);
        Organization result = organizationService.createOrganization(organization);
        return converters.convertToDto(result);
    }

    @PatchMapping("/{organizationId}/add-user/{userId}")
    public OrganizationDto addUserToOrganization(@PathVariable Long organizationId, @PathVariable Long userId) {
        Organization organization = organizationService.addUserToOrganization(organizationId, userId);
        return converters.convertToDto(organization);
    }

    @PatchMapping("/{organizationId}/delete-user/{userId}")
    public OrganizationDto deleteUserFromOrganization(@PathVariable Long organizationId, @PathVariable Long userId) {
        Organization organization = organizationService.deleteUserFromOrganization(organizationId, userId);
        return converters.convertToDto(organization);
    }

    @GetMapping("/{organizationId}")
    public Set<UserDto> getUsersFromOrganization(@PathVariable Long organizationId) {
        Set<User> users = organizationService.getUsersFromOrganization(organizationId);
        return users.stream()
                .map(converters::convertToDto)
                .collect(Collectors.toSet());
    }
}
