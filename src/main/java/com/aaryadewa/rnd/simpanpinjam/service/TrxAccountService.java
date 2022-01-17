package com.aaryadewa.rnd.simpanpinjam.service;

import java.util.Optional;

import com.aaryadewa.rnd.simpanpinjam.domain.TrxAccount;
import com.aaryadewa.rnd.simpanpinjam.repository.postgresql.TrxAccountRepository;
import com.aaryadewa.rnd.simpanpinjam.service.dto.TrxAccountDTO;
import com.aaryadewa.rnd.simpanpinjam.service.mapper.TrxAccountMapper;
import com.querydsl.core.types.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TrxAccount}.
 */
@Service
@Transactional
public class TrxAccountService {

    public static final String KAFKA_TOPIC_NAME = "trx-account";

    private final Logger log = LoggerFactory.getLogger(TrxAccountService.class);

    private final TrxAccountRepository trxAccountRepository;

    private final TrxAccountMapper trxAccountMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public TrxAccountService(
        TrxAccountRepository trxAccountRepository,
        TrxAccountMapper trxAccountMapper,
        KafkaTemplate<String, Object> kafkaTemplate
    ) {
        this.trxAccountRepository = trxAccountRepository;
        this.trxAccountMapper = trxAccountMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Save a trxAccount.
     *
     * @param trxAccountDTO the entity to save.
     * @return the persisted entity.
     */
    public TrxAccountDTO save(TrxAccountDTO trxAccountDTO) {
        log.debug("Request to save TrxAccount : {}", trxAccountDTO);
        TrxAccount trxAccount = trxAccountMapper.toEntity(trxAccountDTO);
        trxAccount = trxAccountRepository.save(trxAccount);
        TrxAccountDTO dto = trxAccountMapper.toDto(trxAccount);

        // Send trxAccount to a Kafka topic.
        kafkaTemplate.send(KAFKA_TOPIC_NAME, dto);

        return dto;
    }

    /**
     * Partially update a trxAccount.
     *
     * @param trxAccountDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TrxAccountDTO> partialUpdate(TrxAccountDTO trxAccountDTO) {
        log.debug("Request to partially update TrxAccount : {}", trxAccountDTO);

        return trxAccountRepository
            .findById(trxAccountDTO.getId())
            .map(existingTrxAccount -> {
                trxAccountMapper.partialUpdate(existingTrxAccount, trxAccountDTO);

                return existingTrxAccount;
            })
            .map(trxAccountRepository::save)
            .map(trxAccountMapper::toDto);
    }

    /**
     * Get all the trxAccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrxAccountDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("Request to get all TrxAccounts");
        return trxAccountRepository.findAll(predicate, pageable).map(trxAccountMapper::toDto);
    }

    /**
     * Get one trxAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrxAccountDTO> findOne(Long id) {
        log.debug("Request to get TrxAccount : {}", id);
        return trxAccountRepository.findById(id).map(trxAccountMapper::toDto);
    }

    /**
     * Delete the trxAccount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TrxAccount : {}", id);
        trxAccountRepository.deleteById(id);
    }
}
