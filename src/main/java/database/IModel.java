package database;

public interface IModel {
  public void setId(Integer id);
  public Integer getId();
  public void setCreatedAt(Long createdAt);
  public Long getCreatedAt();
  public void setUpdatedAt(Long updatedAt);
  public Long getUpdatedAt();
  public String toString();
}
