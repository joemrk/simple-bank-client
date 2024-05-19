package account;

import transaction.Transaction;
import transaction.TransactionService;
import transaction.TransactionStatus;

public class AccountService {
  private final AccountRepository accountRepository;

  private final TransactionService transactionService;

  public AccountService(TransactionService transactionService) {

    this.accountRepository = new AccountRepository();
    this.transactionService = transactionService;
  }

  public Account createOne(Integer currencyId, Integer userId) {
    Account account = new Account(currencyId, userId);
    return accountRepository.create(account);
  }

  public Account[] getByUser(Integer userId) {
    return accountRepository.getByUser(userId);
  }

  public Transaction transfer(Integer accountFromId, Integer accountToId, Double amount) throws Exception {
    Account fromAcc = this.accountRepository.findById(accountFromId);

    if(fromAcc == null) {
      throw new Exception("From Account not found");
    }

    if(fromAcc.getBalance() < amount) {
      throw new Exception("Not enough money");
    }

    Account toAcc = this.accountRepository.findById(accountToId);
    if(toAcc == null) {
      throw new Exception("To Account not found");
    }

    if(!fromAcc.getCurrencyId().equals(toAcc.getCurrencyId())) {
      throw new Exception("Currency is different");
    }

    Transaction tx = this.transactionService.createOne(accountFromId, accountToId, amount);

    // must create some tx with status PENDING first then update it
    // START transaction
    this.transactionService.updateStatus(tx.getId(), TransactionStatus.SUCCESS);

    fromAcc.setBalance(fromAcc.getBalance() - amount);
    this.accountRepository.update(fromAcc);

    toAcc.setBalance(toAcc.getBalance() + amount);
    this.accountRepository.update(toAcc);
    // END transaction

    return tx;
  }


}
