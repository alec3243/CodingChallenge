package com.codingchallenge.integration;

import com.codingchallenge.dto.OrganizationDto;
import com.codingchallenge.dto.UserDto;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    private RestTemplate patchRestTemplate;

    @Before
    public void setup() {
        patchRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @Test
    public void shouldCreateOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName("org name");
        var result = restTemplate.postForObject(String.format("http://localhost:%d/organizations", port), organization, OrganizationDto.class);
        organization.setId(result.getId());
        assertEquals(organization, result);
    }

    @Test
    public void shouldAddUserToOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName("org name");
        var resultOrg = restTemplate.postForObject(String.format("http://localhost:%d/organizations", port), organization, OrganizationDto.class);

        UserDto user = new UserDto();
        user.setFirstName("username");
        var resultUser = restTemplate.postForObject(String.format("http://localhost:%d/users", port), user, UserDto.class);

        var result = patchRestTemplate.patchForObject(String.format("http://localhost:%d/organizations/%d/add-user/%d", port, resultOrg.getId(), resultUser.getId()), null, OrganizationDto.class);
        resultOrg.setUsers(new HashSet<>(Collections.singletonList(user)));
        assertEquals(resultOrg, result);
    }

    @Test
    public void shouldDeleteUserFromOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName("org name");
        var resultOrg = restTemplate.postForObject(String.format("http://localhost:%d/organizations", port), organization, OrganizationDto.class);

        UserDto user = new UserDto();
        user.setFirstName("username");
        var resultUser = restTemplate.postForObject(String.format("http://localhost:%d/users", port), user, UserDto.class);

        patchRestTemplate.patchForObject(String.format("http://localhost:%d/organizations/%d/add-user/%d", port, resultOrg.getId(), resultUser.getId()), null, OrganizationDto.class);
        resultOrg.setUsers(new HashSet<>(Collections.singletonList(user)));

        var finalResultOrg = patchRestTemplate.patchForObject(String.format("http://localhost:%d/organizations/%d/delete-user/%d", port, resultOrg.getId(), resultUser.getId()), null, OrganizationDto.class);

        var resultUsers = restTemplate.getForObject(String.format("http://localhost:%d/organizations/%d", port, finalResultOrg.getId()), Set.class);
        assertTrue(((Set<UserDto>)resultUsers).isEmpty());
    }
}
