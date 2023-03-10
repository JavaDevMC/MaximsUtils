package com.mysql.cj.xdevapi;

import java.util.List;

public interface Schema extends DatabaseObject {
  List<Collection> getCollections();
  
  List<Collection> getCollections(String paramString);
  
  List<Table> getTables();
  
  List<Table> getTables(String paramString);
  
  Collection getCollection(String paramString);
  
  Collection getCollection(String paramString, boolean paramBoolean);
  
  Table getCollectionAsTable(String paramString);
  
  Table getTable(String paramString);
  
  Table getTable(String paramString, boolean paramBoolean);
  
  Collection createCollection(String paramString);
  
  Collection createCollection(String paramString, boolean paramBoolean);
  
  Collection createCollection(String paramString, CreateCollectionOptions paramCreateCollectionOptions);
  
  void modifyCollection(String paramString, ModifyCollectionOptions paramModifyCollectionOptions);
  
  void dropCollection(String paramString);
  
  public static class CreateCollectionOptions {
    private Boolean reuseExisting = null;
    
    private Validation validation = null;
    
    public CreateCollectionOptions setReuseExisting(boolean reuse) {
      this.reuseExisting = Boolean.valueOf(reuse);
      return this;
    }
    
    public Boolean getReuseExisting() {
      return this.reuseExisting;
    }
    
    public CreateCollectionOptions setValidation(Validation validation) {
      this.validation = validation;
      return this;
    }
    
    public Validation getValidation() {
      return this.validation;
    }
  }
  
  public static class ModifyCollectionOptions {
    private Validation validation = null;
    
    public ModifyCollectionOptions setValidation(Validation validation) {
      this.validation = validation;
      return this;
    }
    
    public Validation getValidation() {
      return this.validation;
    }
  }
  
  public static class Validation {
    public enum ValidationLevel {
      STRICT, OFF;
    }
    
    private ValidationLevel level = null;
    
    private String schema = null;
    
    public Validation setLevel(ValidationLevel level) {
      this.level = level;
      return this;
    }
    
    public ValidationLevel getLevel() {
      return this.level;
    }
    
    public Validation setSchema(String schema) {
      this.schema = schema;
      return this;
    }
    
    public String getSchema() {
      return this.schema;
    }
  }
}


/* Location:              C:\Users\Win10\Desktop\VoxilityCraftServer\Bungee\BungeeCord.jar!\com\mysql\cj\xdevapi\Schema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */