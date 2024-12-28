package com.example.fishing_pokedex.migration;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration2To3 extends Migration {
    public Migration2To3() {
        super(2, 3);
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {
        // double weight -> Double weight
        database.execSQL("CREATE TABLE fish_table_new ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "name TEXT, "
                + "weight REAL, " // Column is now nullable
                + "isCaught INTEGER NOT NULL, "
                + "photoPath TEXT)");

        // Copy the data from the old table to the new table
        database.execSQL("INSERT INTO fish_table_new (id, name, weight, isCaught, photoPath) "
                + "SELECT id, name, weight, isCaught, photoPath FROM fish_table");

        // Drop the old table
        database.execSQL("DROP TABLE fish_table");

        // Rename the new table to the old table name
        database.execSQL("ALTER TABLE fish_table_new RENAME TO fish_table");
    }
}

