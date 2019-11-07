package com.codingchallenge.services;

import com.codingchallenge.exceptions.ResourceNotFoundException;
import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import com.codingchallenge.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OrganizationService {

    private OrganizationRepository organizationRepository;
    private UserService userService;

    public OrganizationService(OrganizationRepository organizationRepository, UserService userService) {
        this.organizationRepository = organizationRepository;
        this.userService = userService;
    }

    /**
     * Gets an organization by ID.
     * @param id ID to use for query
     * @return The resulting organization
     */
    private Organization getOrganizationById(Long id) {
        Optional<Organization> optional = organizationRepository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException("No organization exists with id " + id));
    }

    /**
     * Creates an organization
     * @param organization Organization to be created
     * @return The resulting organization
     */
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    /**
     * Adds a user to an organization
     * @param organizationId ID for organization
     * @param userId ID for user
     * @return The resulting organization
     */
    public Organization addUserToOrganization(Long organizationId, Long userId) {
        Organization organization = getOrganizationById(organizationId);
        User user = userService.getUserById(userId);
        organization.getUsers().add(user);
        return organizationRepository.save(organization);
    }

    /**
     * Deletes a user from an organization
     * @param organizationId ID for organization
     * @param userId ID for user
     * @return The resulting organization
     */
    public Organization deleteUserFromOrganization(Long organizationId, Long userId) {
        Organization organization = getOrganizationById(organizationId);
        User user = userService.getUserById(userId);
        Set<User> users = organization.getUsers();
        if (users.contains(user)) {
            users.remove(user);
        } else {
            throw new ResourceNotFoundException("User does not exist in organization");
        }
        return organizationRepository.save(organization);
    }

    /**
     * Gets all users that belong to an organization
     * @param id ID for organization
     * @return The collection of users belonging to the organization
     */
    public Set<User> getUsersFromOrganization(Long id) {
        Organization organization = getOrganizationById(id);
        return organization.getUsers();
    }
}
