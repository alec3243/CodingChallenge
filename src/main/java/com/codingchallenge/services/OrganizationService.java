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

    private Organization getOrganizationById(Long id) {
        Optional<Organization> optional = organizationRepository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException("No organization exists with id " + id));
    }

    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization addUserToOrganization(Long organizationId, Long userId) {
        Organization organization = getOrganizationById(organizationId);
        User user = userService.getUserById(userId);
        organization.getUsers().add(user);
        return organizationRepository.save(organization);
    }

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

    public Set<User> getUsersFromOrganization(Long id) {
        Organization organization = getOrganizationById(id);
        return organization.getUsers();
    }
}
