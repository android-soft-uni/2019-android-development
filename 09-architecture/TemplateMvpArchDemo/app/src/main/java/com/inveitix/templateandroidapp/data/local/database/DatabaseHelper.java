package com.inveitix.templateandroidapp.data.local.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.inveitix.templateandroidapp.data.local.database.dao.DeviceRecordDao;
import com.inveitix.templateandroidapp.data.local.database.dao.UserSettingsDao;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE = "CREATE TABLE ";
    public static final String SELECT_ALL = "SELECT * ";
    public static final String DELETE_FROM = "DELETE FROM ";
    public static final String FROM = " FROM ";
    public static final String SELECT = "SELECT ";
    public static final String WHERE = " WHERE ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String LIKE = " LIKE ";
    public static final String TEXT = " TEXT ";
    public static final String INTEGER = " INTEGER ";
    public static final String NOT_NULL = " NOT NULL ";
    public static final String COMMA = ", ";
    public static final String AND = " AND ";
    public static final String DESC = " DESC";
    public static final String ASC = " ASC";
    public static final String ARGUMENT_MATCHER = "=?";
    public static final String ARGUMENT_NOT_EQUAL = "<>?";
    public static final String ARGUMENT_GT = ">?";
    public static final String ARGUMENT_GT_E = ">=?";
    public static final String ARGUMENT_LT = "<?";
    public static final String ARGUMENT_LT_E = "<=?";
    public static final String ARGUMENT_IN = "  IN (%s)";
    public static final String ALTER_TABLE = "ALTER TABLE ";
    public static final String ADD_COLUMN = " ADD COLUMN ";
    public static final String CREATE_INDEX = "CREATE INDEX ";
    public static final String ON = " ON ";
    public static final String BRACKET_FORMAT = " (%s) ";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "local.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserSettingsDao.CREATE_TABLE_QUERY);
        sqLiteDatabase.execSQL(DeviceRecordDao.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        DatabaseUpgrades.upgrade(sqLiteDatabase, oldVersion);
    }
}
