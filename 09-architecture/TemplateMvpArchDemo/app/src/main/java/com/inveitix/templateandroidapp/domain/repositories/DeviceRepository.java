package com.inveitix.templateandroidapp.domain.repositories;

import android.bluetooth.BluetoothDevice;

import com.inveitix.templateandroidapp.data.local.database.records.DeviceRecord;

public interface DeviceRepository {
    DeviceRecord saveDevice(int type, BluetoothDevice device);
    DeviceRecord getDeviceById(String id);
    DeviceRecord getDevice(int type);
    DeviceRecord getDevice(String name, String address);
    void setDeviceConnected(int type, boolean connected);
    void updateDeviceRecord(DeviceRecord record);

    boolean isConnected(int type);
}
