package com.ciandt.worldwonders.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by nlopes on 8/24/15.
 */
public class WondersSQLiteHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wonders.db";
    private static final String DATABASE_DIRECTORY = "data/data/com.ciandt.worldwonders/databases/";
    private static final String DATABASE_PATH = DATABASE_DIRECTORY + DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private static WondersSQLiteHelper wondersSQLiteHelper;


    public static WondersSQLiteHelper getInstance(Context context) {

        if (wondersSQLiteHelper == null) {
            wondersSQLiteHelper = new WondersSQLiteHelper(context);
        }

        return wondersSQLiteHelper;
    }

    public void configureDataBase () throws IOException {

        File file = new File(DATABASE_PATH);

        if (!file.exists()) {
            copyDataBase();
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

        while ((length = myInput.read(buffer))>0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    private WondersSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //roda o scrpit do banco
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //roda o script de update
    }
}
