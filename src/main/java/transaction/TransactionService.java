package transaction;

import java.util.List;

public class TransactionService {

  private final TransactionRepository transactionRepository;

  public TransactionService() {
    this.transactionRepository = new TransactionRepository();
  }

  public Transaction createOne(Integer accountFromId, Integer accountToId, Double amount) {
    Transaction transaction = new Transaction(accountFromId, accountToId, amount);
    return transactionRepository.create(transaction);
  }

  public List<Transaction> getByPeriodOfAccount(String accountId, String start, String end) {
    return transactionRepository.getByPeriodOfAccount(Integer.parseInt(accountId), Long.parseLong(start), Long.parseLong(end));
  }

  public Transaction updateStatus(Integer txId, TransactionStatus status) throws Exception {
    Transaction exist = this.transactionRepository.findById(txId);
    if(exist == null) {
      throw new Exception("Transaction not found");
    }

    exist.setStatus(status);
    this.transactionRepository.update(exist);

    return exist;
  }
}
