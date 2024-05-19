package transaction;

import database.Model;

public class Transaction extends Model {
  private Integer id;
  private Long createdAt;
  private Long  updatedAt;
  private Integer fromAccountId;
  private Integer toAccountId;
  private Double amount;
  private TransactionStatus status;

  public Transaction(Integer id, Long createdAt, Long updatedAt, Integer fromAccountId, Integer toAccountId, Double amount, TransactionStatus status) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.fromAccountId = fromAccountId;
    this.toAccountId = toAccountId;
    this.amount = amount;
    this.status = status;
  }
  public Transaction(Integer accountFromId, Integer accountToId, Double amount) {
    this.fromAccountId = accountFromId;
    this.toAccountId = accountToId;
    this.amount = amount;
    this.status = TransactionStatus.PENDING;
  }

  public Transaction() {}

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

  public Integer getFromAccountId() {
    return fromAccountId;
  }

  public void setFromAccountId(Integer fromAccountId) {
    this.fromAccountId = fromAccountId;
  }

  public Integer getToAccountId() {
    return toAccountId;
  }

  public void setToAccountId(Integer toAccountId) {
    this.toAccountId = toAccountId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  public void setStatus(TransactionStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Transaction{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", fromAccountId=" + fromAccountId +
            ", toAccountId=" + toAccountId +
            ", amount=" + amount +
            ", status=" + status +
            '}';
  }
}
