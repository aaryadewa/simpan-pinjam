package com.aaryadewa.rnd.simpanpinjam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({ "com.aaryadewa.rnd.simpanpinjam.repository" })
@EnableTransactionManagement
public class PostgresqlConfiguration {}
