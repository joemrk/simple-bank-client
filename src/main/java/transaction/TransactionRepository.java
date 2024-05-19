package transaction;

import database.Repository;

import java.util.List;

public class TransactionRepository extends Repository<Transaction> {


  public TransactionRepository() {
    super(Transaction.class);
  }

  public List<Transaction> getByPeriodOfAccount(Integer accountId, Long start, Long end) {
    return this.findAll().stream().filter(
            tx -> tx.getCreatedAt() >= start && tx.getCreatedAt() <= end
                    && (tx.getFromAccountId().equals(accountId) || tx.getToAccountId().equals(accountId)))
            .toList();
  }

}
