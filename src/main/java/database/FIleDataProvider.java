package database;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class FIleDataProvider {
  private File file;

  public FIleDataProvider(String table) {
    String dataFiles = "src/main/java/database/tables"; // to config
    Path path = Paths.get(System.getProperty("user.dir"), dataFiles, table.toLowerCase() + ".csv");
    System.out.println(System.getProperty("user.dir"));
    File file = new File(path.toString());

    if(!file.exists()) {
      try {
        file.createNewFile();
      } catch (Exception e) {
        System.out.println(e);
        throw new RuntimeException(String.format("Can't create file. Reason: ",e.getMessage()));
      }
    }

    this.file = file;
  }

  public HashMap<Integer, String> findAll() {
    Integer row = 0;
    HashMap<Integer, String> lines = new HashMap<>();
    try (Scanner myReader = new Scanner(this.file);) {
      while (myReader.hasNextLine()) {
        row++;
        String data = myReader.nextLine();
        if(data.startsWith("##")) continue;

        lines.put(row, data);
      }
    } catch (Exception e) {
      throw new RuntimeException(String.format("Can't read file. Reason: ", e.getMessage()));
    }

    return lines;
  }

  public String findOne(Integer row) {
    // small file
    // String line = Files.readAllLines(Paths.get(this.file)).get(row);

    // large file
     try (Stream<String> lines = Files.lines(Paths.get(this.file.getAbsolutePath()), StandardCharsets.UTF_8)) {
        return lines.skip(row - 1).findFirst().get();
     } catch (Exception e) {
        throw new RuntimeException(String.format("Row not foued. Reason: ", e.getMessage()));
     }
  }

  public boolean addOne(String line) {
    try {
      FileWriter fw = new FileWriter(this.file, true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(line);
      bw.newLine();
      bw.close();
      return true;
    } catch (Exception e) {
      throw new RuntimeException(String.format("Can't write to file. Reason: ", e.getMessage()));
    }
  }

  public boolean updateOne(Integer row, String replace) {
      try {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        int lineIndex = 1;
        while ((line = reader.readLine()) != null) {
          if (lineIndex == row) {
            contentBuilder.append(replace).append("\n");
          } else {
            contentBuilder.append(line).append("\n");
          }
          lineIndex++;
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.file.getAbsolutePath()));
        writer.write(contentBuilder.toString());
        writer.close();

        return true;
      } catch (Exception e) {
        System.out.println(e);
        throw new RuntimeException(String.format("Can't updateOne. Reason: ", e.getMessage()));
      }
  }

  public boolean deleteOne(Integer row) {
    // mark as deleted, add ## on start
    String replace = "##" + this.findOne(row);
    return this.updateOne(row, replace);
  }
}
