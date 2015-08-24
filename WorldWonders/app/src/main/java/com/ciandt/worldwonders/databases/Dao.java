package com.ciandt.worldwonders.databases;

import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by nlopes on 8/24/15.
 */
public interface Dao {

    List getAll();
    Wonder getById(int id);
    List search(String wonder);
    boolean update(Wonder wonder);
    boolean delete(int id);
    void close();

}
