package com.inveitix.templateandroidapp.data.local.database;


import android.database.Cursor;

public interface CursorConverter<T extends Record> {
    T fromCursor(Cursor cursor);
}
