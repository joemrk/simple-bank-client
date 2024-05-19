package transaction;

import utils.Controller;

import java.util.stream.Collectors;

public class TransactionController extends Controller {

  private TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;

    try {
      this.setMethod("getByPeriodOfAccount", this.getClass().getDeclaredMethod("getByPeriodOfAccount", String.class, String.class, String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void createOne(String accountFromId, String accountToId, String amount) {
    Transaction newTx = transactionService.createOne(Integer.parseInt(accountFromId), Integer.parseInt(accountToId), Double.parseDouble(amount));
    System.out.println(newTx.toString());
  }

  public void getByPeriodOfAccount(String accountId, String start, String end) {
    System.out.println(transactionService.getByPeriodOfAccount(accountId, start, end).stream().map(Transaction::toString).collect(Collectors.toList()));
  }
}