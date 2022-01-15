package com.aaryadewa.rnd.simpanpinjam.repository.postgresql;

import com.aaryadewa.rnd.simpanpinjam.domain.ExtUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExtUser entity.
 */
@Repository
public interface ExtUserRepository extends JpaRepository<ExtUser, Long>, JpaSpecificationExecutor<ExtUser> {}
