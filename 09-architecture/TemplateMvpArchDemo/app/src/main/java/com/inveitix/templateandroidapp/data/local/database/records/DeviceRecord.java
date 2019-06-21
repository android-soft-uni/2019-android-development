package com.inveitix.templateandroidapp.data.local.database.records;

import android.content.ContentValues;
import android.database.Cursor;

import com.inveitix.templateandroidapp.data.local.database.Record;

import static com.inveitix.templateandroidapp.data.local.database.dao.DeviceRecordDao.COLUMN_ADDRESS;
import static com.inveitix.templateandroidapp.data.local.database.dao.DeviceRecordDao.COLUMN_CONNECTED;
import static com.inveitix.templateandroidapp.data.local.database.dao.DeviceRecordDao.COLUMN_NAME;
import static com.inveitix.templateandroidapp.data.local.database.dao.DeviceRecordDao.COLUMN_REMOTE_ID;
import static com.inveitix.templateandroidapp.data.local.database.dao.DeviceRecordDao.COLUMN_TYPE;

public class DeviceRecord extends Record<DeviceRecord> {

    private String name;
    private String address;
    private boolean connected;
    private String type;
    private String remoteId;

    @Override
    protected void setValues(ContentValues values) {
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CONNECTED, connected);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_REMOTE_ID, remoteId);
    }

    @Override
    protected void parseFields(Cursor cursor) {
        setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
        setAddress(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)));
        setConnected(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONNECTED)) > 0);
        setType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)));
        setRemoteId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REMOTE_ID)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }
}
