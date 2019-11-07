package com.codingchallenge.services;

import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import com.codingchallenge.repositories.OrganizationRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceTest {

    private OrganizationService organizationService;
    private Organization organization;
    private User user;
    private User user1;

    @Mock
    private OrganizationRepository organizationRepository;
    @Mock
    private UserService userService;

    @Before
    public void setup() {
        organizationService = new OrganizationService(organizationRepository, userService);

        user = new User();
        user.setFirstName("firstName");

        user1 = new User();
        user.setFirstName("firstname1");

        organization = new Organization();
        organization.setName("test name");
        organization.setUsers(new HashSet<>(Collections.singletonList(user)));
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(organizationRepository, userService);
    }

    @Test
    public void shouldCreateOrganization() {
        when(organizationRepository.save(organization)).thenReturn(organization);

        var result = organizationService.createOrganization(organization);
        assertEquals(organization, result);

        verify(organizationRepository).save(organization);
    }

    @Test
    public void shouldAddUserToOrganization() {
        Organization testOrganization = new Organization();
        testOrganization.setName("test name");
        testOrganization.setUsers(new HashSet<>());

        when(organizationRepository.findById(1L)).thenReturn(Optional.of(testOrganization));
        when(userService.getUserById(1L)).thenReturn(user);
        when(organizationRepository.save(organization)).thenReturn(organization);

        var result = organizationService.addUserToOrganization(1L, 1L);
        assertEquals(organization, result);

        verify(organizationRepository).findById(1L);
        verify(userService).getUserById(1L);
        verify(organizationRepository).save(organization);
    }

    @Test
    public void shouldDeleteUserFromOrganization() {
        Organization testOrganization = new Organization();
        testOrganization.setName("test name");
        testOrganization.setUsers(new HashSet<>(Arrays.asList(user, user1)));

        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(testOrganization));
        when(userService.getUserById(1L)).thenReturn(user1);
        when(organizationRepository.save(organization)).thenReturn(organization);

        var result = organizationService.deleteUserFromOrganization(1L, 1L);
        assertEquals(organization, result);

        verify(organizationRepository).findById(1L);
        verify(userService).getUserById(1L);
        verify(organizationRepository).save(organization);
    }

    @Test
    public void shouldGetUsersFromOrganization() {
        when(organizationRepository.findById(1L)).thenReturn(Optional.of(organization));

        var result = organizationService.getUsersFromOrganization(1L);
        assertEquals(organization.getUsers(), result);

        verify(organizationRepository).findById(1L);
    }
}
