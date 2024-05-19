package account;

import transaction.Transaction;
import utils.Controller;

public class AccountController extends Controller {
  private AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;

    try {
      this.setMethod("createOne", this.getClass().getDeclaredMethod("createOne", String.class, String.class));
      this.setMethod("getByUser", this.getClass().getDeclaredMethod("getByUser", String.class));
      this.setMethod("transfer", this.getClass().getDeclaredMethod("transfer", String.class, String.class, String.class));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void createOne(String currencyId, String userId) {
    Account newAccount = accountService.createOne(Integer.parseInt(currencyId), Integer.parseInt(userId));
    System.out.println(newAccount.toString());
  }

  public void getByUser(String userId) {
    Account[] accounts = accountService.getByUser(Integer.parseInt(userId));
    System.out.println(java.util.Arrays.toString(accounts));
  }

  public void transfer(String fromAcc, String toAcc, String amount) {
    try {
      Transaction tx = this.accountService.transfer(Integer.parseInt(fromAcc), Integer.parseInt(toAcc), Double.parseDouble(amount));
      System.out.println(tx.toString());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
