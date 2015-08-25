package com.ciandt.worldwonders.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ciandt.worldwonders.databases.Dao;
import com.ciandt.worldwonders.databases.WonderDao;
import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nlopes on 8/25/15.
 */
public class WondersRepository {

    private Context context;
    private List<AsyncTask> tasks;

    public WondersRepository(Context context) {

        this.context = context;
        tasks = new ArrayList<AsyncTask>();

    }

    @NonNull
    public void getAll(final WonderAllListener wonderAllListener) {

        AsyncTask<Void, Void, List<Wonder>> asyncTask = new AsyncTask<Void, Void, List<Wonder>>() {

            @Override
            protected List<Wonder> doInBackground(Void... params) {

                Dao<Wonder> dao = new WonderDao(context);
                List<Wonder> result = dao.getAll();
                dao.close();

                return result;

            }

            @Override
            protected void onPostExecute(List<Wonder> wonders) {

                super.onPostExecute(wonders);
                wonderAllListener.onWonderAll(null, wonders);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);
        asyncTask.execute();

    }

    @NonNull
    public void getRandon(final int quantityItems, final WonderRandomListener wonderRandomListener) {

        AsyncTask<Void, Void, List<Wonder>> asyncTask = new AsyncTask<Void, Void, List<Wonder>>() {

            @Override
            protected List<Wonder> doInBackground(Void... params) {

                Dao<Wonder> dao = new WonderDao(context);
                List<Wonder> result = dao.getRandon(quantityItems);
                dao.close();
                
                return result;

            }

            @Override
            protected void onPostExecute(List<Wonder> wonders) {

                super.onPostExecute(wonders);
                wonderRandomListener.onWonderRandom(null, wonders);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);
        asyncTask.execute();

    }

    public interface WonderAllListener {
        void onWonderAll(Exception e, List<Wonder> wonders);
    }

    public interface WonderRandomListener {
        void onWonderRandom(Exception e, List<Wonder> wonders);
    }

    public void cancel() {
        for (AsyncTask asyncTask: tasks) {

            if(!asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }

        }

    }
}
