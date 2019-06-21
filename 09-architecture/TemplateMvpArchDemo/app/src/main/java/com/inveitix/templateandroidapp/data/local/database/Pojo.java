package com.inveitix.templateandroidapp.data.local.database;

import android.content.ContentValues;

import java.io.Serializable;

abstract class Pojo implements Serializable {

    private static final long serialVersionUID = -8154004945606524125L;

    public abstract ContentValues getValues();

}

