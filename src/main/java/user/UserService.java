package user;

import java.util.List;

public class UserService {

  private UserRepository userRepository;

  public UserService() {
    this.userRepository = new UserRepository();
  }

  public User createOne(String name, String email, String password) {
    User user = new User(name, email, password);
    return userRepository.create(user);
  }

  public User getById(Integer id) {
    return userRepository.findById(id);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }
}
