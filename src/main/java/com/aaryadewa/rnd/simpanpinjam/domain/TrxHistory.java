package com.aaryadewa.rnd.simpanpinjam.domain;

import com.aaryadewa.rnd.simpanpinjam.domain.enumeration.TransactionType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A TrxHistory.
 */
@Document(collection = "trx_history")
public class TrxHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("account_no")
    private Long accountNo;

    @NotNull
    @Size(min = 3, max = 20)
    @Field("user_name")
    private String userName;

    @NotNull
    @Size(max = 40)
    @Field("first_name")
    private String firstName;

    @Size(max = 40)
    @Field("last_name")
    private String lastName;

    @NotNull
    @Field("amount")
    private BigDecimal amount;

    @NotNull
    @Field("trx_date")
    private ZonedDateTime trxDate;

    @NotNull
    @Field("trx_type")
    private TransactionType trxType;

    public String getId() {
        return this.id;
    }

    public TrxHistory id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAccountNo() {
        return this.accountNo;
    }

    public TrxHistory accountNo(Long accountNo) {
        this.setAccountNo(accountNo);
        return this;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserName() {
        return this.userName;
    }

    public TrxHistory userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public TrxHistory firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public TrxHistory lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public TrxHistory amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTrxDate() {
        return this.trxDate;
    }

    public TrxHistory trxDate(ZonedDateTime trxDate) {
        this.setTrxDate(trxDate);
        return this;
    }

    public void setTrxDate(ZonedDateTime trxDate) {
        this.trxDate = trxDate;
    }

    public TransactionType getTrxType() {
        return this.trxType;
    }

    public TrxHistory trxType(TransactionType trxType) {
        this.setTrxType(trxType);
        return this;
    }

    public void setTrxType(TransactionType trxType) {
        this.trxType = trxType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrxHistory)) {
            return false;
        }
        return id != null && id.equals(((TrxHistory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrxHistory{" +
            "id=" + getId() +
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
