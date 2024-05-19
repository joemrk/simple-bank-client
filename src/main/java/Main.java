
/*
 * + create user
 * + get user by id
 * + get users
 * + create account of user
 * + account list by user
 * - create transfer between accounts + update account balance
 * - transactions by period for account
 *
 * - unit tests
 *
* */

import account.AccountController;
import account.AccountService;
import transaction.TransactionController;
import transaction.TransactionService;
import user.UserController;
import user.UserService;
import utils.Controller;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
//    try {
//      String configFilePath = "src/config.properties";
//      FileInputStream propsInput = new FileInputStream(configFilePath);
//
//      Properties prop = new Properties();
//      prop.load(propsInput);
//
//      System.out.println(prop.getProperty("DB_USER"));
//    } catch (Exception e) {
//      System.out.println("Config file does not exist. Create a config.properties file in the src folder.");
//    }


    UserService userService = new UserService();
    TransactionService transactionService = new TransactionService();
    AccountService accountService = new AccountService(transactionService);

    HashMap<String, Controller> controllers = new HashMap<>();

    controllers.put("user", new UserController(userService));
    controllers.put("account", new AccountController(accountService));
    controllers.put("transaction", new TransactionController(transactionService));

    Commander commander = new Commander(controllers);
    commander.listen();
  }
}