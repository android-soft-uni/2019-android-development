package com.inveitix.templateandroidapp.data.local.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.inveitix.templateandroidapp.data.local.database.DatabaseHelper.ARGUMENT_MATCHER;

public abstract class RecordManager<T extends Record> implements CursorConverter<T> {
    private static final String TAG = "RecordManager";

    protected static final String COLUMN_ID = "_ID";
    protected static final String COLUMN_CREATED_ON = "CREATED_ON";
    protected static final String COLUMN_EDITED_ON = "EDITED_ON";

    protected static final String META_COLUMNS_QUERY_SUBSET = COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_CREATED_ON + " INTEGER, "
            + COLUMN_EDITED_ON + " INTEGER, ";

    private DatabaseHelper databaseHelper;

    public RecordManager(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insert(T record) {
        record.setId(insert(record.getValues()));
        return record.getId();
    }

    public void insert(List<T> records) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.beginTransaction();
        for (T record : records) {
            record.setId(insert(record.getValues()));
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void update(T record) {
        update(record.getValues(), COLUMN_ID + "=?", new String[]{
                String.valueOf(record.getId())
        });
    }

    public void update(List<T> records) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.beginTransaction();

        for (T record : records) {
            update(record.getValues(), COLUMN_ID + "=?", new String[]{
                    String.valueOf(record.getId())
            });
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public T findById(long id) {
        try (Cursor query = query(null, COLUMN_ID + ARGUMENT_MATCHER, new String[]{String.valueOf(id)}, null, null, null, null)) {
            if (query != null && query.moveToFirst()) {
                return fromCursor(query);
            }
            return null;
        }
    }

    public List<T> findAll() {
        try (Cursor query = query(null, null, null, null, null, null, null)) {
            return listFromCursor(query);
        }
    }

    public boolean delete(T record) {
        return delete(COLUMN_ID + ARGUMENT_MATCHER, new String[]{String.valueOf(record.getId())}) > 0;
    }

    public abstract String getTableName();

    public List<T> listFromCursor(Cursor cursor) {
        ArrayList<T> list = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                list.add(fromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return list;
    }

    protected long insert(ContentValues values) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        setInsertMetaValues(values);
        return database.insert(getTableName(), "", values);
    }

    protected int update(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        setUpdateMetaValues(values);
        return database.update(getTableName(), values, whereClause, whereArgs);
    }

    protected Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        return database.query(getTableName(), columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    protected Cursor rawQuery(String query, String[] queryParams) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        return database.rawQuery(query, queryParams);
    }

    protected int delete(String whereClause, String[] whereArgs) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        return database.delete(getTableName(), whereClause, whereArgs);
    }

    protected void execSQL(String sql) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.execSQL(sql);
    }

    protected boolean notEmptyCursor(Cursor cursor) {
        return !isCursorEmpty(cursor);
    }

    protected boolean isCursorEmpty(Cursor cursor) {
        return cursor == null || cursor.getCount() == 0;
    }

    protected boolean notEmptyCursorMoveToFirst(Cursor cursor) {
        boolean notEmpty = notEmptyCursor(cursor);
        if (notEmpty) {
            cursor.moveToFirst();
        }
        return notEmpty;
    }

    protected boolean checkEmptyAndClose(Cursor cursor) {
        if (cursor == null) {
            return true;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            return true;
        }
        return false;
    }

    private void setInsertMetaValues(ContentValues values) {
        values.put(COLUMN_CREATED_ON, System.currentTimeMillis());
        setUpdateMetaValues(values);
    }

    private void setUpdateMetaValues(ContentValues values) {
        values.put(COLUMN_EDITED_ON, System.currentTimeMillis());
    }
}
