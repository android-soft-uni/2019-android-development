package com.inveitix.templateandroidapp.data.local.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUpgrades {

    private static List<DatabaseUpgrade> upgrades = new ArrayList<>();

    public static void upgrade(SQLiteDatabase database, int oldVersion) {
        for (DatabaseUpgrade upgrade : getUpgrades()) {
            if (upgrade.getVersion() > oldVersion) {
                upgrade.doUpgrade(database);
            }
        }
    }

    private static List<DatabaseUpgrade> getUpgrades() {
        if (upgrades.isEmpty()) {
            upgrades.add(new Ver2IndexUpgrade());
        }
        return upgrades;
    }

    interface DatabaseUpgrade {
        int getVersion();

        void doUpgrade(SQLiteDatabase database);
    }

    static class Ver2IndexUpgrade implements DatabaseUpgrade {
        @Override
        public int getVersion() {
            return 2;
        }

        @Override
        public void doUpgrade(SQLiteDatabase database) {
            database.execSQL("");
        }
    }
}
