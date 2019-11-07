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

    /**
     * Creates an organization, and returns the resulting organization
     * @param organizationDto Organization to be created
     * @return The resulting organization
     */
    @PostMapping
    public OrganizationDto createOrganization(@RequestBody OrganizationDto organizationDto) {
        Organization organization = converter.convertToModel(organizationDto);
        Organization result = organizationService.createOrganization(organization);
        return converter.convertToDto(result);
    }

    /**
     * Adds an existing user to an existing organization using the provided IDs. If no records with the matching IDs are found, or if the user already belongs to the organization, then a 500 code is returned.
     * @param organizationId ID for organization
     * @param userId ID for user
     * @return The resulting organization
     */
    @PatchMapping("/{organizationId}/add-user/{userId}")
    public OrganizationDto addUserToOrganization(@PathVariable Long organizationId, @PathVariable Long userId) {
        Organization organization = organizationService.addUserToOrganization(organizationId, userId);
        return converter.convertToDto(organization);
    }

    /**
     * Deletes an existing user from an existing organization.
     * @param organizationId
     * @param userId
     * @return
     */
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
