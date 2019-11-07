package com.codingchallenge.utils;

import com.codingchallenge.dto.OrganizationDto;
import com.codingchallenge.dto.UserDto;
import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    private Converter converter;
    private UserDto userDto;
    private OrganizationDto organizationDto;
    private User user;
    private Organization organization;

    @Before
    public void setup() {
        converter = new Converter();
        userDto = new UserDto(1L, "alec", "strickland", "email@email.com", "123 Street", "555-555-5555", Collections.emptySet());
        organizationDto = new OrganizationDto(2L, "org name", "987 Circle", "123-456-7890", Collections.emptySet());
        user = new User(1L, "alec", "strickland", "email@email.com", "123 Street", "555-555-5555", Collections.emptySet());
        organization = new Organization(2L, "org name", "987 Circle", "123-456-7890", Collections.emptySet());
    }

    @Test
    public void shouldConvertToDto() {
        assertEquals(userDto, converter.convertToDto(user));
        assertEquals(organizationDto, converter.convertToDto(organization));
    }

    @Test
    public void shouldConvertToModel() {
        assertEquals(user, converter.convertToModel(userDto));
        assertEquals(organization, converter.convertToModel(organizationDto));
    }
}
