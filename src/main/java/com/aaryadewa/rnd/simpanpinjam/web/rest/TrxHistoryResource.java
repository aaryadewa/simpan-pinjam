package com.aaryadewa.rnd.simpanpinjam.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.aaryadewa.rnd.simpanpinjam.domain.TrxHistory;
import com.aaryadewa.rnd.simpanpinjam.service.TrxHistoryService;
import com.aaryadewa.rnd.simpanpinjam.service.dto.TrxHistoryDTO;
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
 * {@link com.aaryadewa.rnd.simpanpinjam.domain.TrxHistory}.
 */
@RestController
@RequestMapping("/api")
public class TrxHistoryResource {

    private final Logger log = LoggerFactory.getLogger(TrxHistoryResource.class);

    private final TrxHistoryService trxHistoryService;

    public TrxHistoryResource(
            TrxHistoryService trxHistoryService) {
        this.trxHistoryService = trxHistoryService;
    }

    /**
     * {@code POST  /trx-histories} : Create a new trxHistory.
     *
     * @param trxHistoryDTO the trxHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trxHistoryDTO.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trx-histories")
    public ResponseEntity<TrxHistoryDTO> createTrxHistory(@Valid @RequestBody TrxHistoryDTO trxHistoryDTO)
            throws URISyntaxException {
        log.debug("REST request to save TrxHistory : {}", trxHistoryDTO);
        TrxHistoryDTO result = trxHistoryService.save(trxHistoryDTO);
        return ResponseEntity
            .created(new URI("/api/trx-histories/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /trx-histories/:id} : Updates an existing trxHistory.
     *
     * @param id the id of the trxHistoryDTO to save.
     * @param trxHistoryDTO the trxHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trxHistoryDTO,
     * or with status {@code 500 (Internal Server Error)} if the trxHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trx-histories/{id}")
    public ResponseEntity<TrxHistoryDTO> updateTrxHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TrxHistoryDTO trxHistoryDTO
    ) {
        log.debug("REST request to update TrxHistory : {}, {}", id, trxHistoryDTO);
        TrxHistoryDTO result = trxHistoryService.save(trxHistoryDTO);
        return ResponseEntity
            .ok(result);
    }

    /**
     * {@code PATCH  /trx-histories/:id} : Partial updates given fields of an
     * existing trxHistory, field will ignore if it is null
     *
     * @param id the id of the trxHistoryDTO to save.
     * @param trxHistoryDTO the trxHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trxHistoryDTO,
     * or with status {@code 500 (Internal Server Error)} if the trxHistoryDTO couldn't be updated.
     */
    @PatchMapping(value = "/trx-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TrxHistoryDTO> partialUpdateTrxHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TrxHistoryDTO trxHistoryDTO
    ) {
        log.debug("REST request to partial update TrxHistory partially : {}, {}", id, trxHistoryDTO);
        Optional<TrxHistoryDTO> result = trxHistoryService.partialUpdate(trxHistoryDTO);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code GET  /trx-histories} : get all the trxHistories.
     *
     * @param predicate the predicate which the requested entities should match.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trxHistories in body.
     */
    @GetMapping("/trx-histories")
    public ResponseEntity<List<TrxHistoryDTO>> getAllTrxHistories(
        @QuerydslPredicate(root = TrxHistory.class) Predicate predicate,
        Pageable pageable
    ) {
        log.debug("REST request to get TrxHistories");
        Page<TrxHistoryDTO> page = trxHistoryService.findAll(predicate, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trx-histories/:id} : get the "id" trxHistory.
     *
     * @param id the id of the trxHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trxHistoryDTO,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trx-histories/{id}")
    public ResponseEntity<TrxHistoryDTO> getTrxHistory(@PathVariable String id) {
        log.debug("REST request to get TrxHistory : {}", id);
        Optional<TrxHistoryDTO> trxHistoryDTO = trxHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trxHistoryDTO);
    }

    /**
     * {@code DELETE  /trx-histories/:id} : delete the "id" trxHistory.
     *
     * @param id the id of the trxHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trx-histories/{id}")
    public ResponseEntity<Void> deleteTrxHistory(@PathVariable String id) {
        log.debug("REST request to delete TrxHistory : {}", id);
        trxHistoryService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
