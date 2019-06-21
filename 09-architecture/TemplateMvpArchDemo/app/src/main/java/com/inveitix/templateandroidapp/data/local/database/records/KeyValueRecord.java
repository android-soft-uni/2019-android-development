package com.inveitix.templateandroidapp.data.local.database.records;

import android.content.ContentValues;
import android.database.Cursor;

import com.inveitix.templateandroidapp.data.local.database.Record;

import static com.inveitix.templateandroidapp.data.local.database.dao.UserSettingsDao.COLUMN_KEY;
import static com.inveitix.templateandroidapp.data.local.database.dao.UserSettingsDao.COLUMN_VALUE;

public class KeyValueRecord extends Record<KeyValueRecord> {

    private String key;
    private String value;

    @Override
    protected void setValues(ContentValues values) {
        values.put(COLUMN_KEY, key);
        values.put(COLUMN_VALUE, value);
    }

    @Override
    protected void parseFields(Cursor cursor) {
        setKey(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KEY)));
        setValue(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VALUE)));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
