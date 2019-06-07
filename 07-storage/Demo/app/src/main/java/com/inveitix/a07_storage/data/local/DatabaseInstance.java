package com.inveitix.a07_storage.data.local;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseInstance {

    private final AppDatabase db;
    private static DatabaseInstance instance;

    public static DatabaseInstance getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseInstance(context);
        }
        return instance;
    }

    private DatabaseInstance(Context context) {
        //do nothing
        db = Room
                .databaseBuilder(context, AppDatabase.class, "db-name.db")
                .addMigrations(new Migration(1,2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        //do nothing
                    }
                })
                .build();
    }

    public void insertSingleAsync(User user) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                db.userDao().insertSingle(user);
                return null;
            }
        }.execute();
    }

    public void getAll(DatabaseListener<List<User>> callback) {
        new AsyncTask<Void, Void, List<User>>() {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> users = db.userDao().getAll();
                return users;
            }

            @Override
            protected void onPostExecute(List<User> listOfUsers) {
                super.onPostExecute(listOfUsers);
                callback.onDataReceived(listOfUsers);
            }
        }.execute();
    }

    public interface DatabaseListener<T> {
        void onDataReceived(T data);
    }
}
