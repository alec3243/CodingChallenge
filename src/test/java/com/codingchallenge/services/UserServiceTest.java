package com.codingchallenge.services;

import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import com.codingchallenge.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;
    private User user;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        userService = new UserService(userRepository);

        user = new User();
        user.setFirstName("test name");

        Organization organization = new Organization();
        organization.setName("org name");
        user.setOrganizations(new HashSet<>(Collections.singletonList(organization)));
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void shouldCreateUser() {
        when(userRepository.save(any())).thenReturn(user);

        var result = userService.createUser(user);
        assertEquals(user, result);

        verify(userRepository).save(any());
    }

    @Test
    public void shouldGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = userService.getUserById(1L);
        assertEquals(user, result);

        verify(userRepository).findById(1L);
    }
}
