package com.aaryadewa.rnd.simpanpinjam.repository.postgresql;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.Optional;

import com.aaryadewa.rnd.simpanpinjam.domain.QTrxAccount;
import com.aaryadewa.rnd.simpanpinjam.domain.TrxAccount;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TrxAccount entity.
 */
@Repository

public interface TrxAccountRepository extends JpaRepository<TrxAccount, Long>, QuerydslPredicateExecutor<TrxAccount>, QuerydslBinderCustomizer<QTrxAccount> {

  @Override
  default void customize(QuerydslBindings bindings, QTrxAccount root) {
    bindings.bind(root.amount).all((path, value) -> {
      // IN clause if amount is specified more than two times.
      if (value.size() > 2) {
        return Optional.of(path.in(value));
      }

      BooleanExpression predicate;
      Iterator<? extends BigDecimal> it = value.iterator();
      BigDecimal amountFrom = it.next();

      if (value.size() == 2) {
        // BETWEEN clause if amount is specified two times.
        BigDecimal amountTo = it.next();
        predicate = path.between(amountFrom, amountTo);
      } else {
        // > clause if trxDate is specified one time.
        predicate = path.goe(amountFrom);
      }
      return Optional.of(predicate);
    });

    bindings.bind(root.trxDate).all((path, value) -> {
      // IN clause if trxDate is specified more than two times.
      if (value.size() > 2) {
        return Optional.of(path.in(value));
      }

      BooleanExpression predicate;
      Iterator<? extends ZonedDateTime> it = value.iterator();
      ZonedDateTime dateFrom = it.next();

      if (value.size() == 2) {
        // BETWEEN clause if trxDate is specified two times.
        ZonedDateTime dateTo = it.next();
        predicate = path.between(dateFrom, dateTo);
      } else {
        // > clause if trxDate is specified one time.
        predicate = path.goe(dateFrom);
      }
      return Optional.of(predicate);
    });
  }
}
