package com.ciandt.worldwonders.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ciandt.worldwonders.model.Bookmark;

import java.util.List;

public class BookmarkDao implements Dao<Bookmark> {

    protected SQLiteDatabase dataBase;
    final static String TABLE_BOOKMARKS = "bookmarks";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_ID_WONDER = "idWonders";

    public BookmarkDao(Context context) {

        WondersSQLiteHelper persistenceHelper = WondersSQLiteHelper.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();

    }

    @Override
    public long insert(Bookmark bookmark) {

        ContentValues args = new ContentValues();
        args.put("idWonders", bookmark.getIdWonder());
        removeByIdWonder(bookmark.getIdWonder());
        long result = dataBase.insert(TABLE_BOOKMARKS, null, args);

        return result;

    }

    public boolean removeByIdWonder(int idWonder) {
        String [] valoresASubstituir = {String.valueOf(idWonder)};
        int deleteRow = dataBase.delete(TABLE_BOOKMARKS, COLUNA_ID_WONDER + " = ? " , valoresASubstituir);

        return deleteRow > 1;
    }

    @Override
    public List<Bookmark> getAll() {
        return null;
    }

    @Override
    public Bookmark getById(int id) {
        return null;
    }

    @Override
    public List search(String name) {
        return null;
    }

    @Override
    public boolean update(Bookmark object) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Bookmark> getRandon(int quantityItems) {
        return null;
    }

    @Override
    public void close() {

        if (dataBase != null && dataBase.isOpen()) {
            dataBase.close();
        }
    }

}

