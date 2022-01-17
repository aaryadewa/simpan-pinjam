package com.aaryadewa.rnd.simpanpinjam.service.mapper;

import com.aaryadewa.rnd.simpanpinjam.domain.TrxAccount;
import com.aaryadewa.rnd.simpanpinjam.service.dto.TrxAccountDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link TrxAccount} and its DTO {@link TrxAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = { ExtUserMapper.class })
public interface TrxAccountMapper extends EntityMapper<TrxAccountDTO, TrxAccount> {
    @Mapping(target = "user", source = "user", qualifiedByName = "id")
    TrxAccountDTO toDto(TrxAccount s);
}
