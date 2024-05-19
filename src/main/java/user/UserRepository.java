package user;

import database.Repository;

public class UserRepository extends Repository<User> {
  public UserRepository() {
    super(User.class);
  }
}
