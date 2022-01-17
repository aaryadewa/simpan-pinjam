package com.aaryadewa.rnd.simpanpinjam.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.aaryadewa.rnd.simpanpinjam.domain.ExtUser} entity.
 */
public class ExtUserDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(max = 40)
    private String firstName;

    @Size(max = 40)
    private String lastName;

    private LocalDate birthDate;

    private String address;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtUserDTO)) {
            return false;
        }

        ExtUserDTO extUserDTO = (ExtUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, extUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExtUserDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
