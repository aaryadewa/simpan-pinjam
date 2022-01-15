package com.aaryadewa.rnd.simpanpinjam.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.aaryadewa.rnd.simpanpinjam.service.ExtUserService;
import com.aaryadewa.rnd.simpanpinjam.service.dto.ExtUserDTO;
import com.aaryadewa.rnd.simpanpinjam.util.PaginationUtil;
import com.aaryadewa.rnd.simpanpinjam.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * REST controller for managing {@link com.aaryadewa.rnd.simpanpinjam.domain.ExtUser}.
 */
@RestController
@RequestMapping("/api")
public class ExtUserResource {

    private final Logger log = LoggerFactory.getLogger(ExtUserResource.class);

    private final ExtUserService extUserService;

    public ExtUserResource(ExtUserService extUserService) {
        this.extUserService = extUserService;
    }

    /**
     * {@code POST  /ext-users} : Create a new extUser.
     *
     * @param extUserDTO the extUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new extUserDTO.
     */
    @PostMapping("/ext-users")
    public ResponseEntity<ExtUserDTO> createExtUser(@Valid @RequestBody ExtUserDTO extUserDTO) throws URISyntaxException {
        log.debug("REST request to save ExtUser : {}", extUserDTO);
        ExtUserDTO result = extUserService.save(extUserDTO);
        return ResponseEntity
            .created(new URI("/api/ext-users/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ext-users/:id} : Updates an existing extUser.
     *
     * @param id the id of the extUserDTO to save.
     * @param extUserDTO the extUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extUserDTO,
     * or with status {@code 500 (Internal Server Error)} if the extUserDTO couldn't be updated.
     */
    @PutMapping("/ext-users/{id}")
    public ResponseEntity<ExtUserDTO> updateExtUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ExtUserDTO extUserDTO
    ) {
        log.debug("REST request to update ExtUser : {}, {}", id, extUserDTO);
        ExtUserDTO result = extUserService.save(extUserDTO);
        return ResponseEntity
            .ok(result);
    }

    /**
     * {@code PATCH  /ext-users/:id} : Partial updates given fields of an existing extUser, field will ignore if it is null
     *
     * @param id the id of the extUserDTO to save.
     * @param extUserDTO the extUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extUserDTO,
     * or with status {@code 500 (Internal Server Error)} if the extUserDTO couldn't be updated.
     */
    @PatchMapping(value = "/ext-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExtUserDTO> partialUpdateExtUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ExtUserDTO extUserDTO
    ) {
        log.debug("REST request to partial update ExtUser partially : {}, {}", id, extUserDTO);
        Optional<ExtUserDTO> result = extUserService.partialUpdate(extUserDTO);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code GET  /ext-users} : get all the extUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of extUsers in body.
     */
    @GetMapping("/ext-users")
    public ResponseEntity<List<ExtUserDTO>> getAllExtUsers(Pageable pageable) {
        log.debug("REST request to get ExtUsers by criteria: {}", pageable);
        Page<ExtUserDTO> page = extUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ext-users/:id} : get the "id" extUser.
     *
     * @param id the id of the extUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the extUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ext-users/{id}")
    public ResponseEntity<ExtUserDTO> getExtUser(@PathVariable Long id) {
        log.debug("REST request to get ExtUser : {}", id);
        Optional<ExtUserDTO> extUserDTO = extUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(extUserDTO);
    }

    /**
     * {@code DELETE  /ext-users/:id} : delete the "id" extUser.
     *
     * @param id the id of the extUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ext-users/{id}")
    public ResponseEntity<Void> deleteExtUser(@PathVariable Long id) {
        log.debug("REST request to delete ExtUser : {}", id);
        extUserService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
