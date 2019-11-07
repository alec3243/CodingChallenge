package com.codingchallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationDto that = (OrganizationDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, users);
    }

    @Override
    public String toString() {
        return "OrganizationDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", users=" + users +
                '}';
    }
}
