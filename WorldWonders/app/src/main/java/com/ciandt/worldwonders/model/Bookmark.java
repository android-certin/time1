package com.ciandt.worldwonders.model;

/**
 * Created by nlopes on 8/26/15.
 */
public class Bookmark {

    private int id;
    private int idWonder;

    public Bookmark() {
    }

    public Bookmark(int id, int idWonder) {
        this.id = id;
        this.idWonder = idWonder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWonder() {
        return idWonder;
    }

    public void setIdWonder(int idWonder) {
        this.idWonder = idWonder;
    }
}
