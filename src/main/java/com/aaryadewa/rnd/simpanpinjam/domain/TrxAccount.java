package com.aaryadewa.rnd.simpanpinjam.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.aaryadewa.rnd.simpanpinjam.domain.enumeration.TransactionType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TrxAccount.
 */
@Entity
@Table(name = "trx_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrxAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @DecimalMin(value = "1")
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "trx_date", nullable = false)
    private ZonedDateTime trxDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type", nullable = false)
    private TransactionType trxType;

    @ManyToOne(optional = false)
    @NotNull
    private ExtUser user;

    public Long getId() {
        return this.id;
    }

    public TrxAccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public TrxAccount amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTrxDate() {
        return this.trxDate;
    }

    public TrxAccount trxDate(ZonedDateTime trxDate) {
        this.setTrxDate(trxDate);
        return this;
    }

    public void setTrxDate(ZonedDateTime trxDate) {
        this.trxDate = trxDate;
    }

    public TransactionType getTrxType() {
        return this.trxType;
    }

    public TrxAccount trxType(TransactionType trxType) {
        this.setTrxType(trxType);
        return this;
    }

    public void setTrxType(TransactionType trxType) {
        this.trxType = trxType;
    }

    public ExtUser getUser() {
        return this.user;
    }

    public void setUser(ExtUser extUser) {
        this.user = extUser;
    }

    public TrxAccount user(ExtUser extUser) {
        this.setUser(extUser);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrxAccount)) {
            return false;
        }
        return id != null && id.equals(((TrxAccount) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrxAccount{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", trxDate='" + getTrxDate() + "'" +
            ", trxType='" + getTrxType() + "'" +
            "}";
    }
}
