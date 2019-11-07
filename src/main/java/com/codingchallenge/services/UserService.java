package com.codingchallenge.services;

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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Set<Organization> getOrganizationsFromUser(Long id) {
        User user = getUserById(id);
        return user.getOrganizations();
    }

    User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
