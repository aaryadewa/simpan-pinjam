package com.aaryadewa.rnd.simpanpinjam.service;

import java.util.Optional;

import com.aaryadewa.rnd.simpanpinjam.domain.ExtUser;
import com.aaryadewa.rnd.simpanpinjam.repository.postgresql.ExtUserRepository;
import com.aaryadewa.rnd.simpanpinjam.service.dto.ExtUserDTO;
import com.aaryadewa.rnd.simpanpinjam.service.mapper.ExtUserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ExtUser}.
 */
@Service
@Transactional
public class ExtUserService {

    private final Logger log = LoggerFactory.getLogger(ExtUserService.class);

    private final ExtUserRepository extUserRepository;

    private final ExtUserMapper extUserMapper;

    public ExtUserService(ExtUserRepository extUserRepository, ExtUserMapper extUserMapper) {
        this.extUserRepository = extUserRepository;
        this.extUserMapper = extUserMapper;
    }

    /**
     * Save a extUser.
     *
     * @param extUserDTO the entity to save.
     * @return the persisted entity.
     */
    public ExtUserDTO save(ExtUserDTO extUserDTO) {
        log.debug("Request to save ExtUser : {}", extUserDTO);
        ExtUser extUser = extUserMapper.toEntity(extUserDTO);
        extUser = extUserRepository.save(extUser);
        return extUserMapper.toDto(extUser);
    }

    /**
     * Partially update a extUser.
     *
     * @param extUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ExtUserDTO> partialUpdate(ExtUserDTO extUserDTO) {
        log.debug("Request to partially update ExtUser : {}", extUserDTO);

        return extUserRepository
            .findById(extUserDTO.getId())
            .map(existingExtUser -> {
                extUserMapper.partialUpdate(existingExtUser, extUserDTO);

                return existingExtUser;
            })
            .map(extUserRepository::save)
            .map(extUserMapper::toDto);
    }

    /**
     * Get all the extUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ExtUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ExtUsers");
        return extUserRepository.findAll(pageable).map(extUserMapper::toDto);
    }

    /**
     * Get one extUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ExtUserDTO> findOne(Long id) {
        log.debug("Request to get ExtUser : {}", id);
        return extUserRepository.findById(id).map(extUserMapper::toDto);
    }

    /**
     * Delete the extUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ExtUser : {}", id);
        extUserRepository.deleteById(id);
    }
}
