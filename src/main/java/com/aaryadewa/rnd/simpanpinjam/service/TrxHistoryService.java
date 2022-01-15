package com.aaryadewa.rnd.simpanpinjam.service;

import java.util.Optional;

import com.aaryadewa.rnd.simpanpinjam.domain.TrxHistory;
import com.aaryadewa.rnd.simpanpinjam.repository.mongodb.TrxHistoryRepository;
import com.aaryadewa.rnd.simpanpinjam.service.dto.TrxAccountDTO;
import com.aaryadewa.rnd.simpanpinjam.service.dto.TrxHistoryDTO;
import com.aaryadewa.rnd.simpanpinjam.service.mapper.TrxHistoryMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link TrxHistory}.
 */
@Service
public class TrxHistoryService {

    private final Logger log = LoggerFactory.getLogger(TrxHistoryService.class);

    private final ExtUserService extUserService;

    private final TrxHistoryRepository trxHistoryRepository;

    private final TrxHistoryMapper trxHistoryMapper;

    public TrxHistoryService(ExtUserService extUserService, TrxHistoryRepository trxHistoryRepository, TrxHistoryMapper trxHistoryMapper) {
        this.extUserService = extUserService;
        this.trxHistoryRepository = trxHistoryRepository;
        this.trxHistoryMapper = trxHistoryMapper;
    }

    /**
     * Save a trxHistory.
     *
     * @param trxHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public TrxHistoryDTO save(TrxHistoryDTO trxHistoryDTO) {
        log.debug("Request to save TrxHistory : {}", trxHistoryDTO);
        TrxHistory trxHistory = trxHistoryMapper.toEntity(trxHistoryDTO);
        trxHistory = trxHistoryRepository.save(trxHistory);
        return trxHistoryMapper.toDto(trxHistory);
    }

    /**
     * Partially update a trxHistory.
     *
     * @param trxHistoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TrxHistoryDTO> partialUpdate(TrxHistoryDTO trxHistoryDTO) {
        log.debug("Request to partially update TrxHistory : {}", trxHistoryDTO);

        return trxHistoryRepository
            .findById(trxHistoryDTO.getId())
            .map(existingTrxHistory -> {
                trxHistoryMapper.partialUpdate(existingTrxHistory, trxHistoryDTO);

                return existingTrxHistory;
            })
            .map(trxHistoryRepository::save)
            .map(trxHistoryMapper::toDto);
    }

    /**
     * Get all the trxHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<TrxHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TrxHistories");
        return trxHistoryRepository.findAll(pageable).map(trxHistoryMapper::toDto);
    }

    /**
     * Get one trxHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<TrxHistoryDTO> findOne(String id) {
        log.debug("Request to get TrxHistory : {}", id);
        return trxHistoryRepository.findById(id).map(trxHistoryMapper::toDto);
    }

    /**
     * Delete the trxHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete TrxHistory : {}", id);
        trxHistoryRepository.deleteById(id);
    }

    @KafkaListener(
        topics = TrxAccountService.KAFKA_TOPIC_NAME,
        groupId = "${kafka.consumer.group.id}"
    )
    public void onTrxAccountChange(TrxAccountDTO trxAccount) {
        log.debug("onTrxAccountChange message: {}", trxAccount);
        TrxHistory trxHistory = new TrxHistory();
        trxHistory.accountNo(trxAccount.getId())
            .amount(trxAccount.getAmount())
            .trxType(trxAccount.getTrxType())
            .trxDate(trxAccount.getTrxDate());

        extUserService.findOne(trxAccount.getUser().getId())
            .ifPresent(user ->
                trxHistory.userName(user.getName())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
            );

        trxHistoryRepository.save(trxHistory);
    }
}
