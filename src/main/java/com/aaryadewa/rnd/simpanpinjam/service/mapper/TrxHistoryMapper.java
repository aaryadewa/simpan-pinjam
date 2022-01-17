package com.aaryadewa.rnd.simpanpinjam.service.mapper;

import com.aaryadewa.rnd.simpanpinjam.domain.TrxHistory;
import com.aaryadewa.rnd.simpanpinjam.service.dto.TrxHistoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TrxHistory} and its DTO {@link TrxHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrxHistoryMapper extends EntityMapper<TrxHistoryDTO, TrxHistory> {}
