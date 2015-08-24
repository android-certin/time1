package com.ciandt.worldwonders.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WondersSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wonders.db";
    private static final String DATABASE_DIRECTORY = "data/data/com.ciandt.worldwonders/databases/";
    private static final String DATABASE_PATH = DATABASE_DIRECTORY + DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    private static WondersSQLiteHelper instance;
    Context context;

    public static WondersSQLiteHelper getInstance(Context context) {

        if(instance == null)
            instance = new WondersSQLiteHelper(context);

        return instance;
    }


    public WondersSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        configureDataBase();
    }

    public void configureDataBase() {

        File database = new File(DATABASE_PATH);
        if(!database.exists()){
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = context.getAssets().open("databases/" + DATABASE_NAME);
        File file = new File(DATABASE_DIRECTORY);

        if(!file.exists()) {
            file.mkdirs();
        }

        OutputStream myOutput = new FileOutputStream(new File(DATABASE_PATH));

        byte[] buffer = new byte[1024];

        int length;

        while ((length = myInput.read(buffer))>0){

            myOutput.write(buffer, 0, length);

        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //roda o script de update
    }

}
