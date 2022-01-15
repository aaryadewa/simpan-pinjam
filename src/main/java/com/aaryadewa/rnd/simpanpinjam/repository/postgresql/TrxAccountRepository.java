package com.aaryadewa.rnd.simpanpinjam.repository.postgresql;

import com.aaryadewa.rnd.simpanpinjam.domain.TrxAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TrxAccount entity.
 */
@Repository
public interface TrxAccountRepository extends JpaRepository<TrxAccount, Long>, JpaSpecificationExecutor<TrxAccount> {}
