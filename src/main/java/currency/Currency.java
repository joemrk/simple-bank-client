package currency;

import database.Model;

public class Currency extends Model {
  Integer id;
  Long createdAt;
  Long updatedAt;
  String symbol;

  public Currency(Integer id, Long createdAt, Long updatedAt, String symbol) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.symbol = symbol;
  }

  public Currency() {}

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

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return "Currency{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", symbol='" + symbol + '\'' +
            '}';
  }
}
