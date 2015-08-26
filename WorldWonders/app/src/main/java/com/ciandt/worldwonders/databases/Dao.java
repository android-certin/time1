package com.ciandt.worldwonders.databases;

import java.util.List;

/**
 * Created by nlopes on 8/24/15.
 */
public interface Dao<T> {

    public List<T> getAll();
    public T getById(int id);
    public List search(String name);
    public boolean update(T object);
    public boolean delete(int id);
    public void close();
    public List<T> getRandon(int quantityItems);
    public long insert(T Object);

}
