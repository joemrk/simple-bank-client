
import utils.Controller;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

public class Commander {

  private HashMap<String, Controller> controllers ;

  public Commander(HashMap<String, Controller> controllers) {
    this.controllers = controllers;

    System.out.println("Select controller");
    System.out.println(String.format("Available controllers: %s", this.controllers.keySet()));
  }

  public void listen() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String command = scanner.nextLine();

      if (command.equals("exit")) {
        break;
      }
      commandInvoker(command);
    }
  }

  private void commandInvoker(String command) {
    String[] commandParts = command.split(" ");
    String controller = commandParts[0];
    Controller controllerInstance = this.controllers.get(controller);
    if(controllerInstance == null) {
      System.out.println("Controller not found");
      return;
    }
    String action = commandParts[1];
    String[] params = new String[commandParts.length - 2];
    for (int i = 2; i < commandParts.length; i++) {
      params[i - 2] = commandParts[i];
    }

    try {
      Method method = controllerInstance.getMethod(action);
      if(method != null) {
        method.invoke(controllerInstance, (Object[]) params);
      } else {
        System.out.println("Method not found");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
