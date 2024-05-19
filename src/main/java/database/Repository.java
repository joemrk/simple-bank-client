package database;

import utils.Convertor;
import java.util.*;

public abstract class Repository<T extends IModel> {
  private final Class<T> persistentClass;
  private final HashMap<Integer, T> entity;
  private final HashMap<Integer, Integer> entityLines;
  private final FIleDataProvider dataProvider;
  private Integer lastId;
  protected Repository(Class<T> entityClass) {
    this.persistentClass = entityClass;
    this.dataProvider = new FIleDataProvider(persistentClass.getSimpleName());
    this.entity = new HashMap<>();
    this.entityLines = new HashMap<>();

    this.initRepoData();
  }

  private void initRepoData() {
    HashMap<Integer, String> data = this.dataProvider.findAll();
    this.lastId = data.size();
    data.forEach((k, v) -> {
      T instance = null;
      try {
        instance = (T) Class.forName(this.persistentClass.getName()).getDeclaredConstructor().newInstance();
      } catch (Exception e) {
        throw new RuntimeException(String.format("Can't create instance of %s. Reason: %s", this.persistentClass.getName(), e.getMessage()));
      }
      instance = Convertor.fromCsvLine(v, instance);
      this.entity.put(((T) instance).getId(), instance);
      this.entityLines.put(((T) instance).getId(), k);
    });
  }

  public T findById(Integer id) {
    if(this.entity.size() <= 0) {
      throw new RuntimeException("Not found");
    }
    return this.entity.get(id);
  }

  public T create(T entity) {
    Integer id = ++this.lastId;
    entity.setId(id);
    entity.setCreatedAt(new Date().getTime());
    entity.setUpdatedAt(new Date().getTime());
    String csvLine = Convertor.toCsvLine(entity);
    this.dataProvider.addOne(csvLine);
    this.entity.put(id, entity);
    TreeMap<Integer, Integer> treeMap = new TreeMap<>(this.entityLines);

    this.entityLines.put(treeMap.lastKey() + 1, this.lastId);
    return entity;
  }

  public List<T> findAll() {
    return Arrays.stream(this.entity.values().toArray())
            .map(e -> ((T) e))
            .toList();
  }

  public void update(T entity) {
    Integer id = entity.getId();
    if(!this.entity.containsKey(id)) {
      throw new RuntimeException("Not found");
    }
    entity.setUpdatedAt(new Date().getTime());
    this.entity.put(id, entity);
    String csvLine = Convertor.toCsvLine(entity);
    this.dataProvider.updateOne(this.entityLines.get(id), csvLine);
  }

  public void delete(Integer id) {
    if(!this.entity.containsKey(id)) {
      throw new RuntimeException("Not found");
    }
    this.entity.remove(id);
    this.dataProvider.deleteOne(this.entityLines.get(id));
  }

}
