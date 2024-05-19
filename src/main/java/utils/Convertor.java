package utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Convertor {
  public static <T> String toCsvLine(T obj) {
    return Arrays
            .stream(obj.getClass().getDeclaredFields())
            .collect(
                    StringBuilder::new, (sb, f) -> {
                      f.setAccessible(true);
                      try {
                        sb.append(f.get(obj)).append(",");
                      } catch (Exception e) {
                        sb.append("").append(",");
                      } finally {
                        f.setAccessible(false);
                      }
                    },
                    StringBuilder::append
            )
            .toString();
  }

  public static <T> T fromCsvLine(String csvLine, T instance) {
    String[] csvValues = csvLine.split(",");
    if(csvValues.length <= 0) {
      return  instance;
    }

    Field[] fields = instance.getClass().getDeclaredFields();
    if(fields.length != csvValues.length) {
      System.out.println(String.format("csv string [%s] is not match to instance[ %s]", csvLine, instance.getClass().getName()));
      return instance;
    }

    for (int i = 0; i < fields.length; i++) {
      Field f = fields[i];
      f.setAccessible(true);
      // TODO: find how to create map with methods to skip switch

      try {
//        Class<?> dataTypeClass = Class.forName(f.getType().getName());
//        System.out.println(dataTypeClass);

//        Method method = dataTypeClass.getDeclaredMethod("valueOf", String.class);
//        System.out.println(String.format("method: %s", method));

//         f.set(instance, method.invoke(null, csvValues[i]));
        switch (f.getType().getSimpleName()) {
          case "Integer": {
            f.set(instance, Integer.valueOf(csvValues[i]));
            break;
          }
          case "Double": {
            f.set(instance, Double.valueOf(csvValues[i]));
            break;
          }
          case "String": {
            f.set(instance, csvValues[i].toString());
            break;
          }
          case "LocalDate": {
            f.set(instance, Long.valueOf(csvValues[i]));
            break;
          }
          case "Long": {
            f.set(instance, Long.valueOf(csvValues[i]));
          }
          default: break;
        }
      } catch (Exception e) {
        System.out.println(String.format("Can't set value [%s] to field [%s]. Reason: %s", csvValues[i], f.getName(), e.getMessage()));
//        throw e;
      } finally {
        f.setAccessible(false);
      }
    }

    return instance;
  }
}
