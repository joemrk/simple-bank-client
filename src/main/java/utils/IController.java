package utils;

import java.lang.reflect.Method;

public interface IController {
  public Method getMethod(String methodName);
}
