package com.aaryadewa.rnd.simpanpinjam.service.dto;

import com.aaryadewa.rnd.simpanpinjam.domain.enumeration.TransactionType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.aaryadewa.rnd.simpanpinjam.domain.TrxHistory} entity.
 */
public class TrxHistoryDTO implements Serializable {

    private String id;

    @NotNull
    private Long accountNo;

    @NotNull
    @Size(min = 3, max = 20)
    private String userName;

    @NotNull
    @Size(max = 40)
    private String firstName;

    @Size(max = 40)
    private String lastName;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private ZonedDateTime trxDate;

    @NotNull
    private TransactionType trxType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrxHistoryDTO)) {
            return false;
        }

        TrxHistoryDTO trxHistoryDTO = (TrxHistoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, trxHistoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrxHistoryDTO{" +
            "id='" + getId() + "'" +
            ", accountNo=" + getAccountNo() +
            ", userName='" + getUserName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", amount=" + getAmount() +
            ", trxDate='" + getTrxDate() + "'" +
            ", trxType='" + getTrxType() + "'" +
            "}";
    }
}
