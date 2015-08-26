package com.ciandt.worldwonders;

import android.app.Application;
import android.util.Log;

import com.ciandt.worldwonders.databases.WondersSQLiteHelper;
import com.facebook.stetho.Stetho;

import java.io.IOException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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


        setDefaultFont();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

    }

    private void setDefaultFont() {

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Thin.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

}
