package com.ciandt.worldwonders;

import android.app.Application;

import com.ciandt.worldwonders.databases.WonderDao;
import com.ciandt.worldwonders.databases.WondersSQLiteHelper;
import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by nlopes on 8/24/15.
 */
public class WorldWondersApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        WondersSQLiteHelper wondersSQLiteHelper = WondersSQLiteHelper.getInstance(getApplicationContext());
        wondersSQLiteHelper.configureDataBase();


        List<Wonder> wonderList = new WonderDao(getApplicationContext()).getAll();

    }
}
