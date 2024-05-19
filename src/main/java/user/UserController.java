package user;

import utils.Controller;
import java.util.stream.Collectors;

public class UserController extends Controller {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;

    try {
      this.setMethod("getAll", this.getClass().getDeclaredMethod("getAll"));
      this.setMethod("getById", this.getClass().getDeclaredMethod("getById", String.class));
      this.setMethod("createOne", this.getClass().getDeclaredMethod("createOne", String.class, String.class, String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getAll() {
    System.out.println(userService.getAll().stream().map(User::toString).collect(Collectors.toList()));
  }

  public void getById (String id) {
    System.out.println(userService.getById(Integer.parseInt(id)).toString());
  }

  public void createOne(String name, String email, String password) {
    System.out.println(userService.createOne(name, email, password).toString());
  }
}
