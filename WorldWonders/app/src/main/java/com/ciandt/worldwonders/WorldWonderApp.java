package com.ciandt.worldwonders;

import android.app.Application;
import android.util.Log;

import com.ciandt.worldwonders.databases.WondersSQLiteHelper;

import java.io.IOException;

public class WorldWonderApp extends Application{



    @Override
    public void onCreate() {

        super.onCreate();

        WondersSQLiteHelper wondersSQLiteHelper =
                WondersSQLiteHelper.getInstance(getApplicationContext());

        try {
            wondersSQLiteHelper.configureDataBase();
        } catch (IOException e) {
            Log.e("bError", "Erro ao configurar a conexao com o banco de dados");
        }

    }

}
