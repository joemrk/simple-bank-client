package account;

import database.Repository;

public class AccountRepository extends Repository<Account> {

  public AccountRepository() {
    super(Account.class);
  }

  public Account[] getByUser(Integer userId) {
    return this.findAll().stream().filter(account -> account.userId.equals(userId)).toArray(Account[]::new);
  }
}
