package com.codingchallenge.utils;

import com.codingchallenge.dto.OrganizationDto;
import com.codingchallenge.dto.UserDto;
import com.codingchallenge.models.Organization;
import com.codingchallenge.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Utility class containing methods for converting data to/from their respective DTO/model forms
 */
@Component
public class Converter {

    private ModelMapper modelMapper;

    public Converter() {
        this.modelMapper = new ModelMapper();
    }

    public OrganizationDto convertToDto(Organization organization) {
        return modelMapper.map(organization, OrganizationDto.class);
    }

    public Organization convertToModel(OrganizationDto organizationDto) {
        return modelMapper.map(organizationDto, Organization.class);
    }

    public UserDto convertToDto(User organization) {
        return modelMapper.map(organization, UserDto.class);

    }

    public User convertToModel(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
