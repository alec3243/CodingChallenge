package com.codingchallenge.integration;

import com.codingchallenge.dto.OrganizationDto;
import com.codingchallenge.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

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
    public void shouldCreateUser() {
        UserDto user = new UserDto();
        user.setFirstName("test name");

        var result = restTemplate.postForObject(String.format("http://localhost:%d/users", port), user, UserDto.class);
        user.setId(result.getId());
        assertEquals(user, result);
    }

    @Test
    public void shouldGetOrganizationsFromUser() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName("org name");
        var resultOrg = restTemplate.postForObject(String.format("http://localhost:%d/organizations", port), organization, OrganizationDto.class);
        organization.setId(resultOrg.getId());
        UserDto user = new UserDto();
        user.setFirstName("username");
        var resultUser = restTemplate.postForObject(String.format("http://localhost:%d/users", port), user, UserDto.class);
        resultUser.setOrganizations(new HashSet<>(Collections.singletonList(organization)));

        patchRestTemplate.patchForObject(String.format("http://localhost:%d/organizations/%d/add-user/%d", port, resultOrg.getId(), resultUser.getId()), null, OrganizationDto.class);

        var finalResult = restTemplate.getForObject(String.format("http://localhost:%d/users/%d", port, resultUser.getId()), Object.class);
        ObjectMapper mapper = new ObjectMapper();
        assertTrue(resultUser.getOrganizations().contains(mapper.convertValue(((ArrayList)finalResult).get(0), OrganizationDto.class)));
    }
}
