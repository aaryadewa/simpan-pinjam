package com.aaryadewa.rnd.simpanpinjam.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.aaryadewa.rnd.simpanpinjam.domain.TrxAccount;
import com.aaryadewa.rnd.simpanpinjam.service.TrxAccountService;
import com.aaryadewa.rnd.simpanpinjam.service.dto.TrxAccountDTO;
import com.aaryadewa.rnd.simpanpinjam.util.PaginationUtil;
import com.aaryadewa.rnd.simpanpinjam.util.ResponseUtil;
import com.querydsl.core.types.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing
 * {@link com.aaryadewa.rnd.simpanpinjam.domain.TrxAccount}.
 */
@RestController
@RequestMapping("/api")
public class TrxAccountResource {

    private final Logger log = LoggerFactory.getLogger(TrxAccountResource.class);

    private final TrxAccountService trxAccountService;

    public TrxAccountResource(
            TrxAccountService trxAccountService) {
        this.trxAccountService = trxAccountService;
    }

    /**
     * {@code POST  /trx-accounts} : Create a new trxAccount.
     *
     * @param trxAccountDTO the trxAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trxAccountDTO.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trx-accounts")
    public ResponseEntity<TrxAccountDTO> createTrxAccount(@Valid @RequestBody TrxAccountDTO trxAccountDTO)
            throws URISyntaxException {
        log.debug("REST request to save TrxAccount : {}", trxAccountDTO);
        TrxAccountDTO result = trxAccountService.save(trxAccountDTO);
        return ResponseEntity
            .created(new URI("/api/trx-accounts/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /trx-accounts/:id} : Updates an existing trxAccount.
     *
     * @param id the id of the trxAccountDTO to save.
     * @param trxAccountDTO the trxAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trxAccountDTO,
     * or with status {@code 500 (Internal Server Error)} if the trxAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trx-accounts/{id}")
    public ResponseEntity<TrxAccountDTO> updateTrxAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TrxAccountDTO trxAccountDTO
    ) {
        log.debug("REST request to update TrxAccount : {}, {}", id, trxAccountDTO);
        TrxAccountDTO result = trxAccountService.save(trxAccountDTO);
        return ResponseEntity
            .ok(result);
    }

    /**
     * {@code PATCH  /trx-accounts/:id} : Partial updates given fields of an
     * existing trxAccount, field will ignore if it is null
     *
     * @param id the id of the trxAccountDTO to save.
     * @param trxAccountDTO the trxAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trxAccountDTO,
     * or with status {@code 500 (Internal Server Error)} if the trxAccountDTO couldn't be updated.
     */
    @PatchMapping(value = "/trx-accounts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TrxAccountDTO> partialUpdateTrxAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TrxAccountDTO trxAccountDTO
    ) {
        log.debug("REST request to partial update TrxAccount partially : {}, {}", id, trxAccountDTO);
        Optional<TrxAccountDTO> result = trxAccountService.partialUpdate(trxAccountDTO);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code GET  /trx-accounts} : get all the trxAccounts.
     *
     * @param predicate the predicate which the requested entities should match.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trxAccounts in body.
     */
    @GetMapping("/trx-accounts")
    public ResponseEntity<List<TrxAccountDTO>> getAllTrxAccounts(
        @QuerydslPredicate(root = TrxAccount.class) Predicate predicate,
        Pageable pageable
    ) {
        log.debug("REST request to get TrxAccounts");
        Page<TrxAccountDTO> page = trxAccountService.findAll(predicate, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trx-accounts/:id} : get the "id" trxAccount.
     *
     * @param id the id of the trxAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trxAccountDTO,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trx-accounts/{id}")
    public ResponseEntity<TrxAccountDTO> getTrxAccount(@PathVariable Long id) {
        log.debug("REST request to get TrxAccount : {}", id);
        Optional<TrxAccountDTO> trxAccountDTO = trxAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trxAccountDTO);
    }

    /**
     * {@code DELETE  /trx-accounts/:id} : delete the "id" trxAccount.
     *
     * @param id the id of the trxAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trx-accounts/{id}")
    public ResponseEntity<Void> deleteTrxAccount(@PathVariable Long id) {
        log.debug("REST request to delete TrxAccount : {}", id);
        trxAccountService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
