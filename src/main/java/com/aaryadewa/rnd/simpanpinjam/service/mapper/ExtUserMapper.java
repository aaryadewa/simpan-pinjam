package com.aaryadewa.rnd.simpanpinjam.service.mapper;

import com.aaryadewa.rnd.simpanpinjam.domain.ExtUser;
import com.aaryadewa.rnd.simpanpinjam.service.dto.ExtUserDTO;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link ExtUser} and its DTO {@link ExtUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExtUserMapper extends EntityMapper<ExtUserDTO, ExtUser> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    ExtUserDTO toDtoId(ExtUser extUser);
}
