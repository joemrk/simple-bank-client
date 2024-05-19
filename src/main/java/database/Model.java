package database;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class Model implements IModel {
  public String[] getSchema() {
    return Arrays.stream(getClass().getDeclaredFields()).map(Field::getName).toArray(String[]::new);
  }
}
