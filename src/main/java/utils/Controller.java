package utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Controller implements IController {
  private Map<String, Method> methods = new HashMap<>();

  public Method getMethod(String methodName) {
    return this.methods.get(methodName);
  }

  public void setMethod(String name, Method method) {
    this.methods.put(name, method);
  }
}
