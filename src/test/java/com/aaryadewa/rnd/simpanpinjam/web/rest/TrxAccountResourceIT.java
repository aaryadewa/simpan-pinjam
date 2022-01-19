package com.aaryadewa.rnd.simpanpinjam.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aaryadewa.rnd.simpanpinjam.SimpanPinjamApplication;
import com.aaryadewa.rnd.simpanpinjam.domain.enumeration.TransactionType;
import com.aaryadewa.rnd.simpanpinjam.service.TrxAccountService;
import com.aaryadewa.rnd.simpanpinjam.service.dto.ExtUserDTO;
import com.aaryadewa.rnd.simpanpinjam.service.dto.TrxAccountDTO;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link TrxAccountResource} REST controller.
 */
@SpringBootTest(classes = SimpanPinjamApplication.class)
@AutoConfigureMockMvc
class TrxAccountResourceIT {

    private static final String ENTITY_API_URL = "/api/trx-accounts";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(2500000);

    @Autowired
    private MockMvc restTrxAccountMockMvc;

    @Mock
    private TrxAccountService service;

    @Test
    public void findAllTrxAccounts() throws Exception {
        List<TrxAccountDTO> trxList = new ArrayList<>();
        Page<TrxAccountDTO> pagedList = new PageImpl<>(trxList);

        ExtUserDTO user = new ExtUserDTO();
        user.setId(200L);
        user.setName("john");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAddress("Jl. Anggrek");
        user.setBirthDate(LocalDate.of(1990, 2, 14));

        TrxAccountDTO trxAccountDTO = new TrxAccountDTO();
        trxAccountDTO.setId(100L);
        trxAccountDTO.setTrxDate(ZonedDateTime.now());
        trxAccountDTO.setTrxType(TransactionType.SAVINGS);
        trxAccountDTO.setAmount(DEFAULT_AMOUNT);
        trxAccountDTO.setUser(user);

        trxList.add(trxAccountDTO);

        Mockito.when(service.findAll(null, null))
            .thenReturn(pagedList);

        restTrxAccountMockMvc.perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}
