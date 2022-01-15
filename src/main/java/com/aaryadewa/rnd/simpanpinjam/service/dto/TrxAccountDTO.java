package com.aaryadewa.rnd.simpanpinjam.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.aaryadewa.rnd.simpanpinjam.domain.enumeration.TransactionType;

/**
 * A DTO for the {@link com.aaryadewa.rnd.simpanpinjam.domain.TrxAccount} entity.
 */
public class TrxAccountDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "1")
    private BigDecimal amount;

    @NotNull
    private ZonedDateTime trxDate;

    @NotNull
    private TransactionType trxType;

    private ExtUserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(ZonedDateTime trxDate) {
        this.trxDate = trxDate;
    }

    public TransactionType getTrxType() {
        return trxType;
    }

    public void setTrxType(TransactionType trxType) {
        this.trxType = trxType;
    }

    public ExtUserDTO getUser() {
        return user;
    }

    public void setUser(ExtUserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrxAccountDTO)) {
            return false;
        }

        TrxAccountDTO trxAccountDTO = (TrxAccountDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, trxAccountDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrxAccountDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", trxDate='" + getTrxDate() + "'" +
            ", trxType='" + getTrxType() + "'" +
            ", user=" + getUser() +
            "}";
    }
}
