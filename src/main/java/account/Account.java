package account;

import database.Model;

public class Account extends Model {
  Integer id;
  Long createdAt;
  Long updatedAt;
  Double balance;
  Integer currencyId;
  Integer userId;

  public Account(Integer id, Long createdAt, Long updatedAt, Double balance, Integer currencyId, Integer userId) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.balance = balance;
    this.currencyId = currencyId;
    this.userId = userId;
  }

  public Account(Integer currencyId, Integer userId) {
    this.currencyId = currencyId;
    this.userId = userId;
    this.balance = 0.0;
  }

  public Account() {}


  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public Long getCreatedAt() {
    return createdAt;
  }

  @Override
  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public Long getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Integer getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(Integer currencyId) {
    this.currencyId = currencyId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Account{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", balance=" + balance +
            ", currencyId=" + currencyId +
            ", userId=" + userId +
            '}';
  }
}
