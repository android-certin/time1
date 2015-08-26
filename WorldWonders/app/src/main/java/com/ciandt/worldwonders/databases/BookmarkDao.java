package com.ciandt.worldwonders.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.ciandt.worldwonders.model.Bookmark;

import java.util.ArrayList;
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

    public boolean isChecked(int idWonder) {

        String queryOne = "SELECT * FROM " + TABLE_BOOKMARKS + " where " + COLUNA_ID_WONDER + " = " + idWonder;

        List<Bookmark> result = executeQuery(queryOne);

        return result.size() > 0;
    }

    public boolean removeByIdWonder(int idWonder) {
        String [] valoresASubstituir = {String.valueOf(idWonder)};
        int deleteRow = dataBase.delete(TABLE_BOOKMARKS, COLUNA_ID_WONDER + " = ? ", valoresASubstituir);

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

    private Bookmark getCurrentBookmark (Cursor cursor) {

        int indexID = cursor.getColumnIndex(COLUNA_ID);
        int indexWonder = cursor.getColumnIndex(COLUNA_ID_WONDER);

        int id = cursor.getInt(indexID);
        int idWonder = cursor.getInt(indexWonder);

        return new Bookmark(id, idWonder);

    }

    @Override
    public List search(String query) {return null;}

    private List<Bookmark> executeQuery(String query) {

        Cursor cursor = dataBase.rawQuery(query, null);

        List<Bookmark> result = new ArrayList<Bookmark>();

        if (cursor.moveToFirst()) {

            do {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                Bookmark bookmark = getCurrentBookmark(cursor);

                result.add(bookmark);

            } while (cursor.moveToNext());
        }

        return result;

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

