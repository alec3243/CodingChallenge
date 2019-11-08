package com.codingchallenge.services;

import com.codingchallenge.exceptions.ResourceNotFoundException;
import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import com.codingchallenge.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a user
     * @param user User to be created
     * @return The resulting user
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Gets all organizations which the user belongs to
     * @param id ID for user
     * @return The collection of organizations which the user belongs to
     */
    public Set<Organization> getOrganizationsFromUser(Long id) {
        User user = getUserById(id);
        return user.getOrganizations();
    }

    /**
     * Gets a user by ID
     * @param id ID for user
     * @return The resulting user
     */
    User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user exists with id " + id));
    }
}
