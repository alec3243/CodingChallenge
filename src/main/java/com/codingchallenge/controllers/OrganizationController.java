package com.codingchallenge.controllers;

import com.codingchallenge.dto.OrganizationDto;
import com.codingchallenge.dto.UserDto;
import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import com.codingchallenge.services.OrganizationService;
import com.codingchallenge.utils.Converter;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/organizations", produces = "application/json")
public class OrganizationController {

    private OrganizationService organizationService;
    private Converter converter;

    public OrganizationController(OrganizationService organizationService, Converter converter) {
        this.organizationService = organizationService;
        this.converter = converter;
    }

    @PostMapping
    public OrganizationDto createOrganization(@RequestBody OrganizationDto organizationDto) {
        Organization organization = converter.convertToModel(organizationDto);
        Organization result = organizationService.createOrganization(organization);
        return converter.convertToDto(result);
    }

    @PatchMapping("/{organizationId}/add-user/{userId}")
    public OrganizationDto addUserToOrganization(@PathVariable Long organizationId, @PathVariable Long userId) {
        Organization organization = organizationService.addUserToOrganization(organizationId, userId);
        return converter.convertToDto(organization);
    }

    @PatchMapping("/{organizationId}/delete-user/{userId}")
    public OrganizationDto deleteUserFromOrganization(@PathVariable Long organizationId, @PathVariable Long userId) {
        Organization organization = organizationService.deleteUserFromOrganization(organizationId, userId);
        return converter.convertToDto(organization);
    }

    @GetMapping("/{organizationId}")
    public Set<UserDto> getUsersFromOrganization(@PathVariable Long organizationId) {
        Set<User> users = organizationService.getUsersFromOrganization(organizationId);
        return users.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toSet());
    }
}
