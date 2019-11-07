package com.codingchallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.Set;

public class UserDto {

    @ApiModelProperty(hidden = true)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Set<OrganizationDto> organizations;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email, String address, String phone, Set<OrganizationDto> organizations) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.organizations = organizations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<OrganizationDto> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<OrganizationDto> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(address, userDto.address) &&
                Objects.equals(phone, userDto.phone) &&
                Objects.equals(organizations, userDto.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, address, phone, organizations);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", organizations=" + organizations +
                '}';
    }
}
