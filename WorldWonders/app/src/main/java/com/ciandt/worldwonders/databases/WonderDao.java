package com.ciandt.worldwonders.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

public class WonderDao implements Dao<Wonder>{

    protected SQLiteDatabase dataBase;
    final static String TABLE_WONDERS = "wonders";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NAME = "name";
    private static final String COLUNA_PHOTO = "photo";
    private static final String COLUNA_DESCRIPTION = "description";
    private static final String COLUNA_URL = "url";
    private static final String COLUNA_LATITUDE = "latitude";
    private static final String COLUNA_LONGITUDE = "longitude";

    public WonderDao(Context context) {

        WondersSQLiteHelper persistenceHelper = WondersSQLiteHelper.getInstace(context);
        dataBase = persistenceHelper.getWritableDatabase();

    }

    @Override
    public List getAll() {

        String queryReturnAll = "SELECT * FROM " + TABLE_WONDERS;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Wonder> wonders = createWonders(cursor);

        return wonders;
    }

    private Wonder getCurrentWonder (Cursor cursor) {

        int indexID = cursor.getColumnIndex(COLUNA_ID);
        int indexName = cursor.getColumnIndex(COLUNA_NAME);
        int indexPhoto = cursor.getColumnIndex(COLUNA_PHOTO);
        int indexUrl = cursor.getColumnIndex(COLUNA_URL);
        int indexDescription = cursor.getColumnIndex(COLUNA_DESCRIPTION);
        int indexLatitude= cursor.getColumnIndex(COLUNA_LATITUDE);
        int indexLongitude = cursor.getColumnIndex(COLUNA_LONGITUDE);

        int id = cursor.getInt(indexID);
        String name = cursor.getString(indexName);
        String photo = cursor.getString(indexPhoto);
        String description = cursor.getString(indexDescription);
        String url = cursor.getString(indexUrl);
        double latitude = cursor.getDouble(indexLatitude);
        double longitude = cursor.getDouble(indexLongitude);

       return new Wonder(id, name, description, url, photo, latitude, longitude);

    }

    private List<Wonder> createWonders(Cursor cursor) {

        List<Wonder> wonders = new ArrayList<Wonder>();

        if(cursor == null)
            return wonders;

        try {

            if (cursor.moveToFirst()) {
                do {
                    wonders.add(getCurrentWonder(cursor));
                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return wonders;
    }

    @Override
    public Wonder getById(int id) {

        String queryOne = "SELECT * FROM " + TABLE_WONDERS + " where " + COLUNA_ID + " = " + id;

        List<Wonder> result = search(queryOne);

        if(result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public List search(String query) {

        Cursor cursor = dataBase.rawQuery(query, null);

        List<Wonder> result = new ArrayList<Wonder>();

        if (cursor.moveToFirst()) {

            do {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                Wonder wonder = getCurrentWonder(cursor);

                result.add(wonder);

            } while (cursor.moveToNext());
        }

        return result;

    }


    @Override
    public boolean delete(int id) {

        String [] valoresASubstituir = {String.valueOf(id)};

        int deleteRow = dataBase.delete(TABLE_WONDERS, COLUNA_ID + " = ? " , valoresASubstituir);
        return deleteRow > 0 ? true : false;
    }

   @Override
    public boolean update(Wonder wonder) {

       ContentValues args = new ContentValues();
       args.put("name", wonder.getName());
       args.put("description", wonder.getDescription());
       args.put("url", wonder.getUrl());
       args.put("photo",wonder.getPhoto());
       args.put("latitude", wonder.getLatitude());
       args.put("longitude", wonder.getLongitude());

       int result = dataBase.update(TABLE_WONDERS, args, "_id " + "=" + wonder.getId(), null);

       return result > 0 ? true : false;

    }

    @Override
    public void close() {

        if(dataBase != null && dataBase.isOpen()){
            dataBase.close();
        }
    }
}
