package com.inveitix.templateandroidapp.data.local.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

import static com.inveitix.templateandroidapp.data.local.database.RecordManager.COLUMN_CREATED_ON;
import static com.inveitix.templateandroidapp.data.local.database.RecordManager.COLUMN_EDITED_ON;
import static com.inveitix.templateandroidapp.data.local.database.RecordManager.COLUMN_ID;

public abstract class Record<T extends Record<T>> extends Pojo implements CursorConverter, Serializable {

    private static final long serialVersionUID = 3020448186749167086L;

    private Long id;
    private long createdOn;
    private long editedOn;

    @Override
    final public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CREATED_ON, createdOn);
        values.put(COLUMN_EDITED_ON, editedOn);
        values.put(COLUMN_ID, id);

        setValues(values);

        return values;
    }


    @Override
    public T fromCursor(Cursor cursor) {
        parseMeta(cursor);
        parseFields(cursor);
        return (T) this;
    }

    private void parseMeta(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
        setCreatedOn(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_CREATED_ON)));
        setEditedOn(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_EDITED_ON)));
    }

    protected abstract void setValues(ContentValues values);

    protected abstract void parseFields(Cursor cursor);


    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getEditedOn() {
        return editedOn;
    }

    void setEditedOn(long editedOn) {
        this.editedOn = editedOn;
    }
}
