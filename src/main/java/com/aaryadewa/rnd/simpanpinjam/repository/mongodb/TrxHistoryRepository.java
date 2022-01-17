package com.aaryadewa.rnd.simpanpinjam.repository.mongodb;

import com.aaryadewa.rnd.simpanpinjam.domain.TrxHistory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TrxHistory entity.
 */
@Repository
public interface TrxHistoryRepository extends MongoRepository<TrxHistory, String>, QuerydslPredicateExecutor<TrxHistory> {}
