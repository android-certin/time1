package com.ciandt.worldwonders.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ciandt.worldwonders.databases.BookmarkDao;
import com.ciandt.worldwonders.databases.Dao;
import com.ciandt.worldwonders.model.Bookmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nlopes on 8/25/15.
 */
public class BookmarkRepository {

    private Context context;
    private List<AsyncTask> tasks;
    private BookmarkListener listener;

    public BookmarkRepository(Context context) {

        this.context = context;
        tasks = new ArrayList<AsyncTask>();

    }

    @NonNull
    public int addBookmark(final Bookmark bookmark, final BookmarkListener bookmarkListener) {

        AsyncTask<Void, Void, Long> asyncTask = new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... params) {

                Dao<Bookmark> dao = new BookmarkDao(context);
                long result = dao.insert(bookmark);
                dao.close();

                return result;

            }

            @Override
            protected void onPostExecute(Long isPersist) {

                super.onPostExecute(isPersist);
                bookmarkListener.onAddBookmark(null, isPersist);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);
        asyncTask.execute();

        return 0;
    }

    @NonNull
    public boolean isBookmarked(final int idWonder, final  CheckedbookmarkListener checkedbookmarkListener) {

        AsyncTask<Void, Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {

                Dao<Bookmark> dao = new BookmarkDao(context);
                boolean isChecked = ((BookmarkDao)dao).isChecked(idWonder);
                dao.close();

                return isChecked;

            }

            @Override
            protected void onPostExecute(Boolean isChecked) {

                super.onPostExecute(isChecked);
                checkedbookmarkListener.onCheckedbookmark(null, isChecked);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);
        asyncTask.execute();

        return true;
    }

    public interface BookmarkListener {
        void onAddBookmark(Exception e, Long isPersist);
    }

    public interface CheckedbookmarkListener {
        void onCheckedbookmark(Exception e, Boolean isChecked);
    }

    public void cancel() {
        for (AsyncTask asyncTask: tasks) {

            if(!asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }

        }

    }
}
