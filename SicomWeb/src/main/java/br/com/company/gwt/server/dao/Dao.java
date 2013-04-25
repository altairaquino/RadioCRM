package br.com.company.gwt.server.dao;

import java.io.Serializable;
import java.util.List;


public interface Dao<Entity,PkType extends Serializable> {

    public void remove(PkType object);
    
    public List<Entity> removes(List<Entity> object);

    public Entity findByPrimaryKey(PkType type);

    public Entity store(Entity object);
    
    public List<Entity> stores(List<Entity> objects);

    public List<Entity> loadAll();
    
    public <T> T find(Class<T> arg0, Object arg1);
    
    public Entity find(Serializable arg1);
    
}