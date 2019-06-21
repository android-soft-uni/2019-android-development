package com.inveitix.templateandroidapp.data.local.database.dao;

import android.bluetooth.BluetoothDevice;
import android.database.Cursor;

import com.inveitix.templateandroidapp.data.local.database.DatabaseHelper;
import com.inveitix.templateandroidapp.data.local.database.RecordManager;
import com.inveitix.templateandroidapp.data.local.database.records.DeviceRecord;
import com.inveitix.templateandroidapp.domain.repositories.DeviceRepository;

import static com.inveitix.templateandroidapp.data.local.database.DatabaseHelper.AND;
import static com.inveitix.templateandroidapp.data.local.database.DatabaseHelper.ARGUMENT_MATCHER;
import static com.inveitix.templateandroidapp.data.local.database.DatabaseHelper.CREATE_TABLE;

public class DeviceRecordDao extends RecordManager<DeviceRecord> implements DeviceRepository {
    public static final String TABLE_NAME = "devices";

    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_CONNECTED = "CONNECTED";
    public static final String COLUMN_REMOTE_ID = "REMOTE_ID";

    public final static String CREATE_TABLE_QUERY = CREATE_TABLE + TABLE_NAME + " ("
            + META_COLUMNS_QUERY_SUBSET
            + COLUMN_NAME + " TEXT, "
            + COLUMN_ADDRESS + " TEXT, "
            + COLUMN_TYPE + " INTEGER, "
            + COLUMN_CONNECTED + " INTEGER, "
            + COLUMN_REMOTE_ID + " TEXT"
            + " );";

    public DeviceRecordDao(DatabaseHelper databaseHelper) {
        super(databaseHelper);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public DeviceRecord fromCursor(Cursor cursor) {
        return new DeviceRecord().fromCursor(cursor);
    }

    private DeviceRecord findByType(int type) {
        return findByColumn(COLUMN_TYPE, String.valueOf(type));
    }

    private DeviceRecord findByColumn(String columnName, String value) {
        try (Cursor cursor = query(null, columnName + ARGUMENT_MATCHER, new String[]{value}, null, null, null, null)) {
            if (notEmptyCursorMoveToFirst(cursor)) return fromCursor(cursor);
            return null;
        }
    }

    private DeviceRecord insert(String type, BluetoothDevice device) {
        DeviceRecord record = new DeviceRecord();
        record.setConnected(true);
        record.setAddress(device.getAddress());
        record.setName(device.getName());
        record.setType(type);
        super.insert(record);

        return record;
    }

    private void updateConnected(int type, boolean connected) {
        DeviceRecord dbRecord = findByType(type);
        if (dbRecord != null) {
            dbRecord.setConnected(connected);
            super.update(dbRecord);
        } else {
            // TODO or just log
            throw new IllegalArgumentException("Cannot update a device record when it does not exist for type: " + type);
        }
    }

    @Override
    public DeviceRecord saveDevice(int type, BluetoothDevice device) {
        DeviceRecord record = findByType(type);
        if (record != null) {
            record.setAddress(device.getAddress());
            record.setName(device.getName());
            record.setConnected(true);
            record.setRemoteId(null);
            update(record);
        } else {
            record = insert(String.valueOf(type), device);
        }

        return record;
    }

    @Override
    public DeviceRecord getDevice(int type) {
        return findByColumn(COLUMN_TYPE, String.valueOf(type));
    }

    @Override
    public DeviceRecord getDevice(String name, String address) {
        String selection = COLUMN_NAME + ARGUMENT_MATCHER + AND + COLUMN_ADDRESS + ARGUMENT_MATCHER;
        String[] selectionArgs = {name, address};
        try (Cursor cursor = query(null, selection, selectionArgs, null, null, null, null)) {
            if (notEmptyCursorMoveToFirst(cursor)) return fromCursor(cursor);
            return null;
        }
    }

    @Override
    public DeviceRecord getDeviceById(String id) {
        return findByColumn(COLUMN_ID, id);
    }

    @Override
    public void setDeviceConnected(int type, boolean connected) {
        updateConnected(type, connected);
    }

    @Override
    public boolean isConnected(int type) {
        DeviceRecord record = findByType(type);
        return record != null && record.isConnected();
    }

    @Override
    public void updateDeviceRecord(DeviceRecord record) {
        update(record);
    }
}
