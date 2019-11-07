package com.codingchallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

public class OrganizationDto {

    @ApiModelProperty(hidden = true)
    private Long id;
    private String name;
    private String address;
    private String phone;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Set<UserDto> users;

    public OrganizationDto() {
    }

    public OrganizationDto(Long id, String name, String address, String phone, Set<UserDto> users) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }
}
